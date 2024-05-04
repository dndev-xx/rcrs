package com.otus.kfl.rcrs.mapper

import com.otus.kfl.rcrs.model.RcrsError
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MapperTest {

    private lateinit var mapper: EntityMapper
    private lateinit var sourceEntity: RcrsError

    @Before
    fun init() {
        mapper = EntityMapperImpl()
        sourceEntity = RcrsError(
            code = "1",
            group = "2",
            message = "test error"
        )
    }

    @Test
    fun `when mapping entity error to transfer error object then ok`() {
        val targetDto = mapper.entityErrorToErrorDto(sourceEntity)
        assertEquals(sourceEntity.code, targetDto.code)
        assertEquals(sourceEntity.group, targetDto.group)
        assertEquals(sourceEntity.message, targetDto.message)
        assertNull(sourceEntity.exception)
    }
}