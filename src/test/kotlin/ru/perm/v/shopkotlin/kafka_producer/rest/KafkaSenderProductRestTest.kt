package ru.perm.v.shopkotlin.kafka_producer.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import ru.perm.v.shopkotlin.kafka_producer.service.KafkaProducerProductTopicService

@ExtendWith(SpringExtension::class)
@WebMvcTest(KafkaSenderProductRest::class)
class KafkaSenderProductRestTest(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    lateinit var kafkaProducerProductTopicService: KafkaProducerProductTopicService

    val mapper = ObjectMapper().registerModule(KotlinModule())

    @Test
    fun echoArrayMessage() {
        val products = listOf(
            ProductExtDTO(1, "product1", 10),
            ProductExtDTO(2, "product2", 20)
        )
        val mes = mockMvc.perform(
            MockMvcRequestBuilders.post("/send_product/echo/array_message")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(products))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("Received 2 products", mes.response.contentAsString)
    }
}