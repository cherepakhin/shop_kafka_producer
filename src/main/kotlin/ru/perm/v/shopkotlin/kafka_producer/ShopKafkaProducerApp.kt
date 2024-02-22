package ru.perm.v.shopkotlin.kafka_producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShopKafkaProducerApp {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ShopKafkaProducerApp>(*args)
        }
    }
}
