package com.mydocent.auth.config

import com.mydocent.auth.repository.UserRepository
import com.mydocent.model.oauth.OauthKaKaoInfo
import com.mydocent.model.user.entity.User
import com.mydocent.model.user.enum.OAuth2Type
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class PrincipalOauth2UserService(
    private val userRepository: UserRepository
): DefaultOAuth2UserService(){

    /**
     * 서드파티에 사용자 정보를 요청할 수 있는 access token 을 얻고나서 실행됩니다.
     * 이 때 access token과 같은 정보들이 oAuth2UserRequest 파라미터에 들어있습니다.
     */
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2Info = super.loadUser(userRequest)

        val oauth2User = when(userRequest.clientRegistration.registrationId) {
            OAuth2Type.KAKAO.registrationName -> OauthKaKaoInfo(oauth2Info.attributes)
            else -> throw RuntimeException("지원하지 않는 인증 방식입니다.")
        }
        requireNotNull(oauth2User.getProviderId()) { "oauth 가입에 실패하였습니다." }

        val user = userRepository.save(
            User.signUp(
                oauthId = oauth2User.getProviderId().toLong(),
                nickname = oauth2User.getProviderName(),
                email = oauth2User.getProviderEmail(),
                oAuth2Type = oauth2User.getProvider()
            )
        )

        return PrincipalDetails(user, oauth2Info.attributes)
    }
}