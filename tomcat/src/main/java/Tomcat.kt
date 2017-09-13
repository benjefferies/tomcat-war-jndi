
import org.apache.catalina.startup.Tomcat
import org.apache.tomcat.util.descriptor.web.ContextResource

class TomcatWrapper {
    fun start() {
        val resource = ContextResource().apply {
            name = "jdbc/datasource"
            type="javax.sql.DataSource"
            setProperty("url", "jdbc:h2:mem:DB_CLOSE_DELAY=-1;LOCK_MODE=3")
            setProperty("driverClassName", "org.h2.Driver")
            setProperty("username", "")
            setProperty("password", "")
        }
        val tomcat = Tomcat().apply {
            enableNaming()
            setPort(8080)
            val context = addWebapp("/counter", TomcatWrapper::class.java.getResource("./war-1.0-SNAPSHOT.war").toURI().path)
            context.namingResources.addResource(resource)
            host.appBaseFile.mkdirs()
            start()
        }
        tomcat.server.await()
    }
}

fun main(args: Array<String>) {
    TomcatWrapper().start()
}