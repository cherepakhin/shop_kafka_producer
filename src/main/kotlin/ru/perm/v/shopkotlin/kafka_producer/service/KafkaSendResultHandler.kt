package ru.perm.v.shopkotlin.kafka_producer.service

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.slf4j.LoggerFactory


class KafkaSendResultHandler {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    fun onSuccess(producerRecord: Unit, recordMetadata: RecordMetadata?) {
        logger.info("Message send success : $producerRecord")
    }

    fun onError(producerRecord: ProducerRecord<*, *>, exception: Exception?) {
        logger.info("Message send error : $producerRecord")
    }
}