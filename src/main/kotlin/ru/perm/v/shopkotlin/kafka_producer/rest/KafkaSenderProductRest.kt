package ru.perm.v.shopkotlin.kafka_producer.rest

import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Parameter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.shopkotlin.extdto.ProductExtDTO
import ru.perm.v.shopkotlin.kafka_producer.service.KafkaProducerProductTopicService
import java.lang.String.format
import javax.validation.ConstraintViolation
import javax.validation.Validation

@RestController
@RequestMapping("/send_product")
class KafkaSenderProductRest(val kafkaProducerProductTopicService: KafkaProducerProductTopicService) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    val validator: javax.validation.Validator = Validation.buildDefaultValidatorFactory().validator

    @PostMapping("/echo/array_message")
    fun echoArrayMessage(@RequestBody products: List<ProductExtDTO>): String {
        val info = format("Received %s products", products.size)
        logger.info(info)
        return info
    }

    @PostMapping("/manual")
    @ApiOperation("Send Product DTO to Kafka product_ext_dto_topic with manual convert")
    fun sendProductManualConvert(
        @Parameter(
            description = "DTO of Product."
        )
        @RequestBody productExtDTO: ProductExtDTO
    ): String {
        validate(productExtDTO)
        logger.info("Sent with manual convert. $productExtDTO")
        return kafkaProducerProductTopicService.sendWithManualConvert(productExtDTO)
    }

    @PostMapping("/builder")
    @ApiOperation("Send Product DTO to Kafka product_ext_dto_topic with message builder convert")
    fun sendProduct(
        @Parameter(
            description = "DTO of Product."
        )
        @RequestBody productExtDTO: ProductExtDTO
    ): String {
        validate(productExtDTO)
        logger.info("Sent with MessageBuilder. $productExtDTO")
//        return "OK"
        return kafkaProducerProductTopicService.sendWithMessageBuilder(productExtDTO)
    }

    @PostMapping("/send_list")
    @ApiOperation("Send Product DTO to Kafka product_ext_dto_topic with message builder convert")
    fun sendJsonArrayProduct(
        @Parameter(
            description = "DTO of Product."
        )
        @RequestBody listProductExtDTO: List<ProductExtDTO>
    ): String {
        logger.info("Sent list with MessageBuilder")

        listProductExtDTO.forEach { validate(it) }
        listProductExtDTO.forEach {
            kafkaProducerProductTopicService.sendWithMessageBuilder(it)
        }
        return "OK"
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