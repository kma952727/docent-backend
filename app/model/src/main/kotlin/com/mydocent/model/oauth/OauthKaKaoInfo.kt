package com.mydocent.model.oauth

import com.mydocent.model.user.enum.OAuth2Type

class OauthKaKaoInfo(
    private val attributes: Map<String, Any> = mutableMapOf()
): Oauth2UserInfo {

    override fun getProviderId(): String = attributes["id"].toString()

    override fun getProvider(): OAuth2Type = OAuth2Type.KAKAO

    override fun getProviderEmail(): String? = getKakaoAccount()["email"]

    override fun getProviderName(): String? = getKakaoAccount()["nickname"]

    private fun getKakaoAccount(): Map<String, String> {
        val account = attributes["kakao_account"] as Map<String, Any>
        return account["profile"] as Map<String, String>
    }
}