package org.tty.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.tty.server.service.ZfSpiderService

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    lateinit var zfSpiderService: ZfSpiderService

    private fun printObject(instance:Any) {
        val mapper = ObjectMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        mapper.writeValue(System.out, instance)
        println()
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun testLink() {
        val result = zfSpiderService.link()
        printObject(result)
        println(zfSpiderService.login(result, "pe6gxm"))
    }

    fun testLogin() {

    }

}
