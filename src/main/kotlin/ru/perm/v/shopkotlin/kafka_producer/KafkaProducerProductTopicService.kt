package ru.perm.v.shopkotlin.kafka_producer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import ru.perm.v.shopkotlin.extdto.ProductExtDTO

@Component
class KafkaProducerProductTopicService(private val kafkaTemplate: KafkaTemplate<String, String>) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private val PRODUCT_EXT_TOPIC_NAME = "product_ext_dto_topic"
    fun send(productExtDTO: ProductExtDTO): String {
        logger.info("Send to string topic %s message: %s", PRODUCT_EXT_TOPIC_NAME, productExtDTO)
        val result= kafkaTemplate.send(PRODUCT_EXT_TOPIC_NAME, productExtDTO.toString())
        return result.toString()
    }
}