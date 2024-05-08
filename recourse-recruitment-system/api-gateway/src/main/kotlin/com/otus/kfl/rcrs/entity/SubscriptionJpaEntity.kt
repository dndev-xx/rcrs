package com.otus.kfl.rcrs.entity

import com.otus.kfl.rcrs.enums.SubscriptionCategory
import com.otus.kfl.rcrs.enums.SubscriptionStatus
import jakarta.persistence.*

@Entity
@Table(name = "subscription")
open class SubscriptionJpaEntity : BaseJpaEntity() {

    @Column(name = "is_active", nullable = false)
    open var isActive: Boolean? = true

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: SubscriptionStatus? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    open var category: SubscriptionCategory? = null

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    open var user: UserJpaEntity? = null
}
