package com.mydocent

import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

@Configuration
class HttpConfig {

    @Bean
    fun commonRestTemplate(): RestTemplate {
        val connectionManager: PoolingHttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setDefaultConnectionConfig(
                ConnectionConfig.custom()
                    .setSocketTimeout(5, TimeUnit.SECONDS) // 읽기 시간 초과 타임아웃
                    .setConnectTimeout(3, TimeUnit.SECONDS) // 연결 시간 초과 타임아웃
                    .build()
            )
            .setMaxConnTotal(100) // 커넥션 풀 최대 오픈되는 커넥션 수
            .setMaxConnPerRoute(10) // 커넥션 풀에서 한 IP:포트에 대해 수행할 연결 수 제한
            .build()

        val httpClient: CloseableHttpClient = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .build()

        val requestFactory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestTemplate(requestFactory)
    }
}