package ru.perm.v.shopkotlin.kafka_producer.service

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.SettableListenableFuture


class KafkaProducerTextTopicServiceMockTest {
    @Test
    fun sendStringMessage() {
        val mockTemplate = Mockito.mock(KafkaTemplate::class.java) as KafkaTemplate<String, String>
        val service =
            KafkaProducerTextTopicService(kafkaTemplate = mockTemplate)

        val TOPIC_NAME = "text_topic"
        val future = SettableListenableFuture<SendResult<String, String>>()
        `when`(mockTemplate.send(TOPIC_NAME, "")).thenReturn(future);

        service.sendStringMessage("MESSAGE")

        verify(mockTemplate).send(TOPIC_NAME, "MESSAGE")
    }

}