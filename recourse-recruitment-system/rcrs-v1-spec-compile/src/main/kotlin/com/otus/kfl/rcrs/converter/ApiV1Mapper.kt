package com.otus.kfl.rcrs.converter

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.otus.kfl.rcrs.api.v1.models.BaseRequest
import com.otus.kfl.rcrs.api.v1.models.BaseResponse

val apiV1Mapper: JsonMapper = JsonMapper.builder().run {
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    enable(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL)
    build()
}

@Suppress("unused")
fun apiV1RequestSerialize(request: BaseRequest): String = apiV1Mapper.writeValueAsString(request)

@Suppress("UNCHECKED_CAST", "unused")
fun <T : BaseRequest> apiV1RequestDeserialize(json: String): T =
    apiV1Mapper.readValue(json, BaseRequest::class.java) as T

@Suppress("unused")
fun apiV1ResponseSerialize(response: BaseResponse): String = apiV1Mapper.writeValueAsString(response)

@Suppress("UNCHECKED_CAST", "unused")
fun <T : BaseResponse> apiV1ResponseDeserialize(json: String): T =
    apiV1Mapper.readValue(json, BaseResponse::class.java) as T
