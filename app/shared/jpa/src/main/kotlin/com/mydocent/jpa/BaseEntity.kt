package com.mydocent.jpa

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Column(name = "created_at")
    @Comment("생성 시간")
    @CreatedDate
    var createdAt: LocalDateTime? = null

    @Column(name = "updated_at")
    @Comment("마지막 수정 시간")
    var updatedAt: LocalDateTime? = null

    init {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }
}