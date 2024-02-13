package ru.perm.v.shopkotlin.kafka_producer.rest

import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Parameter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.shopkotlin.kafka_producer.service.KafkaProducerTextTopicService
import java.lang.String.format

@RestController
@RequestMapping("/send_text")
class KafkaSenderTextRest(val kafkaProducerTextTopicService: KafkaProducerTextTopicService) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping
    @ApiOperation("Send text message to Kafka product_ext_dto_topic")
    fun sendText(
        @Parameter(
            description = "Any string."
        )
        @RequestParam message: String
    ): String {
        val logMessage = format("Sent message: %s", message)
        logger.info(logMessage)
        kafkaProducerTextTopicService.sendStringMessage(message)
        return logMessage
    }
}