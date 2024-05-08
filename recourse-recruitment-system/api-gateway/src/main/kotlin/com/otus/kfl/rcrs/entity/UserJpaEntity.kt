package com.otus.kfl.rcrs.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class UserJpaEntity : BaseJpaEntity() {

    @Column(name = "city", nullable = false)
    open var city: String? = null

    @Column(name = "contact", nullable = false)
    open var contact: String? = null

    @OneToOne(mappedBy = "user", cascade = [(CascadeType.ALL)])
    open val subscription: SubscriptionJpaEntity? = null
}