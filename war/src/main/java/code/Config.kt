package code

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.sql.Connection
import javax.naming.InitialContext
import javax.sql.DataSource

@EnableWebMvc //mvc:annotation-driven
@Configuration
@ComponentScan("code")
open class Config : WebMvcConfigurerAdapter() {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    }

    @Bean open fun datasource() : DataSource {
        // Get datasource from jndi
        val datasource = InitialContext().lookup("java:/comp/env/jdbc/datasource") as DataSource
        datasource.connection.prepareStatement("create table if not exists ping (counter INT);").executeUpdate()
        datasource.connection.prepareStatement("insert into ping values (0);").executeUpdate()

        return datasource
    }

    @Bean open fun connection(datasource : DataSource) : Connection {
        return datasource.connection
    }


}