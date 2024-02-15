package ru.perm.v.shopkotlin.kafka_producer.service

import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


class KafkaProducerProductTopicServiceTest {
    //TODO: NO TESTS
    @Test
    fun send() {
        val producerFactory = mock<ProducerFactory<String, String>>()
        val mockProducer = mock<Producer<String, String>>()
        whenever(producerFactory.createProducer()).thenReturn(mockProducer)

//        val mockProducerFactory = mock<ProducerFactory<String, Event>>()
//        val mockProducer = mock<Producer<String, Event>>()

        val captor: ArgumentCaptor<ProducerRecord<*, *>>? =
            ArgumentCaptor.forClass<ProducerRecord<*, *>, ProducerRecord<*, *>>(
                ProducerRecord::class.java
            )
//        val cap = captor?.capture()
//        mockProducer.send()
//        `when`(mockProducer.send(No)).thenReturn(mock(Future::class.java) as Future<RecordMetadata>?)
//
        val kafkaTemplate = KafkaTemplate<String,String>(producerFactory)
        val kafkaProducerTextTopicService = KafkaProducerTextTopicService(kafkaTemplate = kafkaTemplate)

//        kafkaProducerTextTopicService.sendStringMessage("MESSAGE")
//        val mockFuture = mock(Future::class.java)
//        `when`(mockProducer.send(any(ProducerRecord::class.java) as Nothing)).thenReturn(mockFuture as Future<RecordMetadata>?)

//        verify(mockProducer).send(ProducerRecord("my-topic", "my-key", "my-value") as Nothing)

//        val dto=MapperProductExtDTO.map
//        producer.send(ProductExtDTO(10L, "NAME_10", 100L))
//        verify(template, times(1)).send("product_ext_dto_topic", "")
    }
}