package ru.perm.v.shopkotlin.kafka_sender

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaProducerTestTopicTextService {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    @KafkaListener(topics = ["test_topic_text"], groupId = "test_id")
    fun read(message: String) {
        logger.info(message)
    }
}