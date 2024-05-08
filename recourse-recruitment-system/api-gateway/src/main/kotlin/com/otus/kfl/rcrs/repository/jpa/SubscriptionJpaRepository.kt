package com.otus.kfl.rcrs.repository.jpa

import com.otus.kfl.rcrs.entity.SubscriptionJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SubscriptionJpaRepository : JpaRepository<SubscriptionJpaEntity, UUID>