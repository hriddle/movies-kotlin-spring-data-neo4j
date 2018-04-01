package movies.spring.data.neo4j

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

//@Bean
//fun mapperConfigurer() = Jackson2ObjectMapperBuilder().apply {
//    serializationInclusion(JsonInclude.Include.NON_NULL)
//    failOnUnknownProperties(true)
//    featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//    indentOutput(true)
//    modules(listOf(KotlinModule()))
//}

@Configuration
@EntityScan("movies.spring.data.neo4j.domain.model.persistent")
class ApplicationConfig {


}

