package ru.perm.v.shopkotlin.kafka_producer.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaProducerTextTopicService(private val kafkaTemplate: KafkaTemplate<String, String>) {
    private val TEXT_TOPIC_NAME = "text_topic"
    fun sendStringMessage(message: String) {
        kafkaTemplate.send(TEXT_TOPIC_NAME, message)
    }
}