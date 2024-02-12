package ru.perm.v.shopkotlin.kafka_producer

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import ru.perm.v.shopkotlin.kafka_producer.mapper.MapperProductExtDTO

internal class MapperProductExtDtoTest {
    @Test
    fun mapFromDtoToString() {
        val PRODUCT_ID = 10L
        val GROUP_ID = 100L

        val dto = ProductExtDTO(PRODUCT_ID, "NAME_1", GROUP_ID)

        Assertions.assertEquals(
            "{\"n\":10,\"name\":\"NAME_1\",\"groupDtoN\":100}",
            MapperProductExtDTO.mapFromDtoToString(dto)
        )
    }

    @Test
    fun mapFromStringToDto() {
        val PRODUCT_ID = 10L
        val GROUP_ID = 100L

        val dto = MapperProductExtDTO.mapFromStringToDto("{\"n\":10,\"name\":\"NAME_1\",\"groupDtoN\":100}")

        Assertions.assertEquals(ProductExtDTO(PRODUCT_ID, "NAME_1", GROUP_ID), dto)
    }
}