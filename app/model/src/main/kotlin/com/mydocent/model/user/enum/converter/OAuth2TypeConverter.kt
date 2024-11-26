package com.mydocent.model.user.enum.converter

import com.mydocent.model.user.enum.OAuth2Type
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class OAuth2TypeConverter: AttributeConverter<OAuth2Type, String> {

    override fun convertToDatabaseColumn(enumTypeOAuth: OAuth2Type): String = OAuth2Type.KAKAO.code.toString()

    override fun convertToEntityAttribute(stringTypeOAuth: String): OAuth2Type = OAuth2Type.of(stringTypeOAuth.toInt())

}