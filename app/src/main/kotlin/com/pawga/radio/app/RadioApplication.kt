package com.pawga.radio.app

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.pawga"])
@EntityScan(basePackages = ["com.pawga"] )
@EnableJpaRepositories(basePackages = ["com.pawga"])
@OpenAPIDefinition(info = Info(title = "API 'Сервис хранения записей станций'", version = "1.0", description = "API 'Сервис хранения записей станций'"))
class RadioApplication

fun main(args: Array<String>) {
    runApplication<RadioApplication>(*args)
}
