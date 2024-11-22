package com.mydocent.model.oauth

import com.mydocent.model.user.enum.OAuth2Type

interface Oauth2UserInfo {
    fun getProviderId(): String
    fun getProvider(): OAuth2Type
    fun getProviderEmail(): String?
    fun getProviderName(): String?
}