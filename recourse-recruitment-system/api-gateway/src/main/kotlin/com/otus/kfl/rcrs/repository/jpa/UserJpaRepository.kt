package com.otus.kfl.rcrs.repository.jpa

import com.otus.kfl.rcrs.entity.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserJpaRepository : JpaRepository<UserJpaEntity, UUID>