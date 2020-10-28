package org.tty.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.tty.server.seeder.DataBaseSeeder

@SpringBootApplication
class ServerApplication : ApplicationRunner {
    @Autowired
    private lateinit var dataBaseSeeder: DataBaseSeeder;

    override fun run(args: ApplicationArguments?) {
        // insert the seed data when applicationContext is loaded
        dataBaseSeeder.insertData();
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
