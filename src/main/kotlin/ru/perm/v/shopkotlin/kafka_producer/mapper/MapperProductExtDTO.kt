package ru.perm.v.shopkotlin.kafka_producer.mapper

import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object MapperProductExtDTO {
    val mapper = jacksonObjectMapper()
    fun mapFromDtoToString(dto: ProductExtDTO): String {
        return mapper.writeValueAsString(dto)
    }

    fun mapFromStringToDto(s :String): ProductExtDTO {
        return mapper.readValue(s)
    }

}