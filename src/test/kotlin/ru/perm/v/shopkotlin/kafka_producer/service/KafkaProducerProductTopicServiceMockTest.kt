package ru.perm.v.shopkotlin.kafka_producer.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.SettableListenableFuture
import ru.perm.v.shopkotlin.extdto.ProductExtDTO


class KafkaProducerProductTopicServiceMockTest {
    @Test
    fun sendWithManualConvert() {
        val mockTemplate =
            Mockito.mock(KafkaTemplate::class.java) as KafkaTemplate<String, String>
        val service =
            KafkaProducerProductTopicService(kafkaTemplate = mockTemplate)

        val TOPIC_NAME = "product_ext_dto_topic"
        val future = SettableListenableFuture<SendResult<String, String>>()
        `when`(mockTemplate.send(TOPIC_NAME, "{\"n\":10,\"name\":\"name\",\"groupDtoN\":100}"))
            .thenReturn(future);

        val productDto = ProductExtDTO(10, "name", 100)
        val resultSend = service.sendWithManualConvert(productDto)

        verify(mockTemplate).send(TOPIC_NAME, "{\"n\":10,\"name\":\"name\",\"groupDtoN\":100}")
        assertEquals("", resultSend)
    }

}