package code

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.sql.Connection

@Controller
class Controller @Autowired constructor(val connection: Connection) {

    @RequestMapping(path = arrayOf("ping"), method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun ping() : String {
        connection.prepareStatement("update ping set counter = counter + 1;").executeUpdate()
        connection.commit()
        val results = connection.createStatement().executeQuery("select counter from ping;")
        results.next()
        val counter = results.getInt(1)
        return "pinged: " + counter
    }
}