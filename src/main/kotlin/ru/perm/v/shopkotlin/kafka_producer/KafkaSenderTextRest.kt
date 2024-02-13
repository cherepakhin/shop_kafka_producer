package ru.perm.v.shopkotlin.kafka_producer

import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Parameter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import java.lang.String.format
import javax.validation.ConstraintViolation
import javax.validation.Validation

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