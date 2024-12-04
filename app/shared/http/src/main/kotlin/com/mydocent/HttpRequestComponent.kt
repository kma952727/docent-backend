package com.mydocent

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class HttpRequestComponent(
    private val restTemplate: RestTemplate
) {

    /**
     * @return http 요청의 응답 (first 속성 - response body, second 속성 - response status)
     */
    fun <T>getRequest(
        url: String,
        body: Any? = null,
        headersMap: Map<String, String>? = emptyMap()
    ): Pair<T?, Int> = request<T>(method = HttpMethod.GET, url = url, body = body, headersMap = headersMap!!)

    /**
     * @return http 요청의 응답 (first 속성 - response body, second 속성 - response status)
     */
    fun <T>postRequest(
        url: String,
        body: Any? = null,
        headersMap: Map<String, String>? = emptyMap()
    ): Pair<T?, Int> = request<T>(method = HttpMethod.POST, url = url, body = body, headersMap = headersMap!!)

    /**
     * @return http 요청의 응답 (first 속성 - response body, second 속성 - response status)
     */
    fun <T>deleteRequest(
        url: String,
        body: Any? = null,
        headersMap: Map<String, String>? = emptyMap()
    ): Pair<T?, Int> = request<T>(method = HttpMethod.DELETE, url = url, body = body, headersMap = headersMap!!)

    /**
     * @return http 요청의 응답 (first 속성 - response body, second 속성 - response status)
     */
    fun <T>putRequest(
        url: String,
        body: Any? = null,
        headersMap: Map<String, String>? = emptyMap()
    ): Pair<T?, Int> = request<T>(method = HttpMethod.PUT, url = url, body = body, headersMap = headersMap!!)

    /**
     * @return http 요청의 응답 (first 속성 - response body, second 속성 - response status)
     */
    fun <T>patchRequest(
        url: String,
        body: Any? = null,
        headersMap: Map<String, String>? = emptyMap()
    ): Pair<T?, Int> = request<T>(method = HttpMethod.PATCH, url = url, body = body, headersMap = headersMap!!)


    private fun <T>request(
        method: HttpMethod,
        url: String,
        body: Any? = null,
        headersMap: Map<String, String>
    ): Pair<T?, Int> {
        val headers = HttpHeaders().apply {
            headersMap.forEach { (key, value) -> this[key] = value }
        }

        val response = restTemplate.exchange(
            url,
            method,
            HttpEntity(body, headers),
            object : ParameterizedTypeReference<T>() {}
        )
        return Pair(response.body, response.statusCode.toString().toInt())
    }
}