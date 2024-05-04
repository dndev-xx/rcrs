package com.otus.kfl.rcrs.converter

import com.otus.kfl.rcrs.api.v1.models.BaseRequest
import com.otus.kfl.rcrs.api.v1.models.Debug
import com.otus.kfl.rcrs.api.v1.models.ItemRequestObject
import com.otus.kfl.rcrs.api.v1.models.RcrsSystem
import com.otus.kfl.rcrs.api.v1.models.RequestDebugMode
import com.otus.kfl.rcrs.api.v1.models.RequestDebugStub
import com.otus.kfl.rcrs.api.v1.models.VcCommonRequest
import java.time.Instant
import java.util.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1SerializationTest {
    private val request = VcCommonRequest(
        system = RcrsSystem(
            rqUid = UUID.randomUUID().toString(),
            rqTm = Instant.now().toString()
        ),
        debug = Debug(
            mode = RequestDebugMode.STUB,
            stub = RequestDebugStub.SUCCESS
        ),
        sub = ItemRequestObject(
            email = "test@gmail.com",
            props = mutableListOf("kotlin", "java", "c")
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        assertContains(json, Regex("\"requestType\":\\s*\"common\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"success\""))
        assertContains(json, Regex("\"email\":\\s*\"test@gmail.com\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, BaseRequest::class.java) as VcCommonRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"sub": null}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, VcCommonRequest::class.java)
        assertEquals(null, obj.sub)
    }
}
