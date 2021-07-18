package com.github.juliherms.product

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.test.web.servlet.ResultActions

/**
 * Method responsible to convert json to object generic T
 */
inline fun <reified T> ResultActions.bodyTo(mapper: ObjectMapper = jacksonObjectMapper()):T{
    return mapper.readValue(this.andReturn().response.contentAsByteArray)
}