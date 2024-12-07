package com.mydocent.model.auth.vo

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class OAuth2TypeConverter: AttributeConverter<OAuth2Type, String> {

    override fun convertToDatabaseColumn(enumTypeOAuth: OAuth2Type): String = OAuth2Type.KAKAO.code.toString()

    override fun convertToEntityAttribute(stringTypeOAuth: String): OAuth2Type {
        // TODO: db에 oauth2 타입을 숫자로 넣을지 문자로 넣을지 정하기
        return if(stringTypeOAuth == "kakao") {
            OAuth2Type.KAKAO
        } else {
            OAuth2Type.of(stringTypeOAuth.toInt())
        }
    }

}