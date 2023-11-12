package join.famila

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class FamilaApplication

fun main(args: Array<String>) {
    runApplication<FamilaApplication>(*args)
}
