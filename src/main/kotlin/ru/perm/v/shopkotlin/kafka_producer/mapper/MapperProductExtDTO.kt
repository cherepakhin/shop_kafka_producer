package ru.perm.v.shopkotlin.kafka_producer.mapper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ru.perm.v.shopkotlin.extdto.ProductExtDTO

object MapperProductExtDTO {
    val mapper = jacksonObjectMapper()
    fun mapFromDtoToString(dto: ProductExtDTO): String {
        return mapper.writeValueAsString(dto)
    }

    fun mapFromStringToDto(s: String): ProductExtDTO {
        if (s.isEmpty()) {
            throw Exception("Empty string for convert to DTO")
        }
        return mapper.readValue(s)
    }

}