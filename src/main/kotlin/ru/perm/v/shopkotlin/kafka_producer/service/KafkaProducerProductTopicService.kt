package ru.perm.v.shopkotlin.kafka_producer.service


import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import ru.perm.v.shopkotlin.kafka_producer.mapper.MapperProductExtDTO

@Component
class KafkaProducerProductTopicService(
    val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private val PRODUCT_EXT_TOPIC_NAME = "product_ext_dto_topic"

    fun sendWithManualConvert(productExtDTO: ProductExtDTO): String {
        val logMessage =
            java.lang.String.format("Send to topic %s message: %s", PRODUCT_EXT_TOPIC_NAME, productExtDTO)
        logger.info(logMessage)

        val sendingString = convertProductExtDTOToString(productExtDTO)

        return sendStringToTopic(sendingString)
    }

    fun sendStringToTopic(message: String): String {
        val result =
            kafkaTemplate.send(PRODUCT_EXT_TOPIC_NAME, message)
        return result.toString()
    }

    fun sendWithMessageBuilder(productExtDTO: ProductExtDTO): String {
        logger.info("Send to topic $PRODUCT_EXT_TOPIC_NAME message: $productExtDTO")
        val message: Message<ProductExtDTO> = MessageBuilder
            .withPayload(productExtDTO)
            .setHeader(KafkaHeaders.TOPIC, PRODUCT_EXT_TOPIC_NAME)
//            .setHeader("X-Custom-Header", "Custom header here")
            .build()
        val result = kafkaTemplate.send(message)
        return result.toString()
    }

    fun convertProductExtDTOToString(productExtDTO: ProductExtDTO): String {
        return MapperProductExtDTO.mapFromDtoToString(productExtDTO)
    }
}