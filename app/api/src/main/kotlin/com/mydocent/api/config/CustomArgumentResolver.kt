package com.mydocent.api.config

import com.mydocent.utils.api.MyInfo
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


class CustomArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean = parameter.parameterType == MyInfo::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): MyInfo {
        val request = webRequest.nativeRequest as HttpServletRequest
        // TODO : security 의존성 문제로 attr 사용
        val myInfoName = MyInfo::class.java.simpleName

        return request.getAttribute(myInfoName) as MyInfo
    }
}