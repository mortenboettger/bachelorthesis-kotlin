package io.mboettger.bachelorthesis.starter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["io.mboettger.bachelorthesis"]
)
open class ApplicationStarter

fun main(args: Array<String>) {
    runApplication<ApplicationStarter>(*args)
}