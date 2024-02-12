package ru.perm.v.shopkotlin.kafka_producer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducerTextTopicService(private val kafkaTemplate: KafkaTemplate<String, String>) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private val TEXT_TOPIC_NAME = "product_ext_dto_topic"
    fun sendStringMessage(message: String) {
        logger.info("Send to string topic %s message: %s", TEXT_TOPIC_NAME, message)
        kafkaTemplate.send(TEXT_TOPIC_NAME, message)
    }
}