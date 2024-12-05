package com.mydocent.model.auth

interface Oauth2UserInfo {
    fun getProviderId(): String
    fun getProvider(): OAuth2Type
    fun getProviderEmail(): String?
    fun getProviderName(): String?
}