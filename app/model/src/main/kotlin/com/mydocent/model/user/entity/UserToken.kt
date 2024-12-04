package com.mydocent.model.user.entity

import com.mydocent.jpa.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "token")
@Comment("""
    사용자의 인증 토큰(jwt)을 표현
    토큰 무효화, 재발급 등에 사용된다.
""")
class UserToken (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    @Comment("pk")
    val id: Int? = null,

    @Column(name = "user_id")
    @Comment("fk: 토큰과 연결된 사용자")
    val userId: Int,

    @Column(name = "token_type")
    @Comment("토큰 타입")
    val tokenType: String? = "bearer",

    @Column(name = "access_token")
    @Comment("현재 활성화중인 엑세스 토큰")
    val accessToken: String,

    @Column(name = "refresh_token")
    @Comment("현재 활성화중인 리프레시 토큰")
    val refreshToken: String,

    @Column(name = "oauth2_access_token")
    @Comment("oauth2에서 발급받은 access token")
    val oauth2AccessToken: String? = null,

    @Column(name = "access_token_expires_at")
    @Comment("엑세스 토큰 만료 시간")
    val accessTokenExpiredAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "refresh_token_expires_at")
    @Comment("리프레시 토큰 만료 시간")
    val refreshTokenExpiredAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "scope")
    @Comment("oauth2 리소스 서버에서 얻을 수 있는 개인 정보 종류 (ex] email, nickname...)")
    val scope: String? = null
): BaseEntity()