package ru.perm.v.shopkotlin.kafka_producer.service


import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback
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
        logger.info("Send message: $message")
        val futureResult =
            kafkaTemplate.send(PRODUCT_EXT_TOPIC_NAME, message)
        var result = ""
        futureResult.addCallback(
            object : ListenableFutureCallback<SendResult<String?, String?>?> {
                override fun onSuccess(stringKafkaBeanSendResult: SendResult<String?, String?>?) {
                    val recordMetadata = stringKafkaBeanSendResult!!.recordMetadata
                    logger.info("SUCCESS============ $recordMetadata")

                    logger.info("RESULT SENT message $message to topic $PRODUCT_EXT_TOPIC_NAME")
                    val logMessage =
                        "RECORD MEATADATA: topic:${recordMetadata.topic()} partition:${recordMetadata.partition()} offset:${recordMetadata.offset()}"
                    logger.info(logMessage)
                    result = logMessage
                }

                override fun onFailure(throwable: Throwable) {
                    logger.error("ERROR============ $throwable")
                    val logMessage =
                        "ERROR RESULT SENT message = $message to topic %PRODUCT_EXT_TOPIC_NAME because of error $throwable.message"
                    logger.error(logMessage)
                    result = logMessage
                }
            })
        return result
    }


    fun sendWithMessageBuilder(productExtDTO: ProductExtDTO): String {
        logger.info("Send to topic $PRODUCT_EXT_TOPIC_NAME message: $productExtDTO")
        val message: Message<ProductExtDTO> = MessageBuilder
            .withPayload(productExtDTO)
            .setHeader(KafkaHeaders.TOPIC, PRODUCT_EXT_TOPIC_NAME)
//            .setHeader("X-Custom-Header", "Custom header here")
            .build()
        val future = kafkaTemplate.send(message)
        future.get().producerRecord?.let { record ->
            val logMessage = "Sent to topic \"${record.topic()}\" message: \"${record.value()}\"\n"
            logger.info(logMessage)
            return logMessage
        } ?: run {
            val logMessage = "Failed to send message to topic $PRODUCT_EXT_TOPIC_NAME"
            logger.error(logMessage)
            return logMessage
        }
    }

    fun convertProductExtDTOToString(productExtDTO: ProductExtDTO): String {
        return MapperProductExtDTO.mapFromDtoToString(productExtDTO)
    }
}