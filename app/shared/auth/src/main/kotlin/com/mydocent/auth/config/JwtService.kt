package com.mydocent.auth.config

import com.mydocent.model.auth.UserAuthentication
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey


@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val rawSecretKey: String,
): InitializingBean {
    private lateinit var secretKey: SecretKey

    /**
     * userToken 객체를 사용해서 jwt 생성
     *
     * @param userAuthentication jwt payload에 추가된다.
     */
    fun createJwt(userAuthentication: UserAuthentication): String {
        return Jwts.builder()
            .subject("user")
            .claim("email", userAuthentication.email)
            .expiration(userAuthentication.expiration)
            .signWith(secretKey)
            .compact()
    }

    /**
     * jwt에 포함된 사용자 정보를 UserToken 객체로 파싱
     *
     * @param jwt jwt 데이터
     * @return jwt의 페이로드 데이터가 담겨있는 UserToken
     */
    fun parseToUserToken(jwt: String): UserAuthentication {
        val claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(jwt)
            .payload

        return UserAuthentication(
            email = claims["email", String::class.java],
            expiration = claims["expiration", Date::class.java]
        )
    }

    /**
     * jwt가 유효한지 검사한다.
     *
     * @param jwt 검사하고 싶은 token
     * @return 유효성 여부
     */
    fun validateJwt(jwt: String): Boolean {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(rawSecretKey)
        secretKey = Keys.hmacShaKeyFor(keyBytes)
    }
}