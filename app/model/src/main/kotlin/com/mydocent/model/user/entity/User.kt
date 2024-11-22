package com.mydocent.model.user.entity

import com.mydocent.model.user.enum.OAuth2Type
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "user")
@Comment("mydocent 사용자 정보")
class User (

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("pk")
    val id: Int? = null,

    @Column(name = "oauth_id")
    @Comment("oauth2 서버에서 제공하는 고유값")
    val oauthId: Long? = null,

    @Column(name = "nickname")
    @Comment("닉네임")
    val nickname: String,

    @Column(name = "email")
    @Comment("이메일")
    val email: String,

    @Column(name = "login_type")
    @Comment("닉네임")
    val oAuth2Type: OAuth2Type,

    @Column(name = "created_at")
    @Comment("가입 날짜")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    @Comment("최신 수정 날짜")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    @Comment("삭제 날짜")
    val deletedAt: LocalDateTime? = null
) {

    companion object {
        fun signUp(oauthId: Long? = null, nickname: String? = null, email: String? = null, oAuth2Type: OAuth2Type): User =
            User(
                oauthId = oauthId,
                nickname = nickname ?: "없는 이름123",
                email = email ?: "없는 이메일123",
                oAuth2Type = oAuth2Type
            )

    }
}