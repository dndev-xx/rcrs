package com.otus.kfl.rcrs.mapper

import com.otus.kfl.rcrs.api.v1.models.VsError
import com.otus.kfl.rcrs.model.RcrsError
import org.mapstruct.Mapper

@Mapper
interface EntityMapper {

    fun entityErrorToErrorDto(entity: RcrsError): VsError
}
