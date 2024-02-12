package ru.perm.v.shopkotlin.kafka_producer

import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import javax.validation.ConstraintViolation
import javax.validation.Validation

@RestController
@RequestMapping("/send_product")
class KafkaSenderProductRest(val kafkaProducerProductTopicService: KafkaProducerProductTopicService) {

    val validator: javax.validation.Validator = Validation.buildDefaultValidatorFactory().validator
    @PostMapping
    @ApiOperation("Send Product DTO to Kafka product_ext_dto_topic")
    fun sendProduct(
        @Parameter(
            description = "DTO of Product."
        )
        @RequestBody productExtDTO: ProductExtDTO
    ): String {
        validate(productExtDTO)
        return kafkaProducerProductTopicService.send(productExtDTO)
    }

    fun validate(productExtDTO: ProductExtDTO) {
        val violations: MutableSet<ConstraintViolation<ProductExtDTO>> =
            validator.validate(productExtDTO)

        if (violations.isNotEmpty()) {
            var messageError = ""
// OLD STYLE
//            for(violation in violations) {
//                messageError = messageError.plus(violation.message + "\n")
//            }

// USED STREAM
            violations.forEach { violation ->
                messageError = messageError.plus(violation.message + "\n")
            }
            throw Exception("$productExtDTO has errors: $messageError")
        }
    }

}