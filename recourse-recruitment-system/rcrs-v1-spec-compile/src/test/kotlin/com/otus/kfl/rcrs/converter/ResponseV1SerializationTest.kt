package com.otus.kfl.rcrs.converter

import com.otus.kfl.rcrs.api.v1.models.BaseResponse
import com.otus.kfl.rcrs.api.v1.models.CommonResponse
import com.otus.kfl.rcrs.api.v1.models.ResponseResult
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1SerializationTest {

    private val response = CommonResponse(
        result = ResponseResult.SUCCESS
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        assertContains(json, Regex("\"result\":\\s*\"success\""))
        assertContains(json, Regex("\"responseType\":\\s*\"common\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, BaseResponse::class.java) as CommonResponse
        assertEquals(response, obj)
    }
}
