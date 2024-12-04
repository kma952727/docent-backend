package com.mydocent.auth.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * permitAll(): securityContextHolder에 인증 객체가 없어도 인증 실패처리되지 않음
 *
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val principalOauth2UserService : PrincipalOauth2UserService,
    private val oAuth2SuccessHandler: OAuth2SuccessHandler,
    private val mapper: ObjectMapper,
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .sessionManagement { sessionCustomizer -> sessionCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authCustomizer ->
                authCustomizer
                    // TODO : 허용된 IP에서만 요청받을 수 있게 수정하기
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/oauth2/authorization/kakao").permitAll()
                    .requestMatchers("/api/users").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth2Customizer ->
                oauth2Customizer.userInfoEndpoint { userInfoEndpointCustomizer -> userInfoEndpointCustomizer.userService(principalOauth2UserService) }
                oauth2Customizer.successHandler(oAuth2SuccessHandler)
            }
            .exceptionHandling { exceptionCustomizer -> exceptionCustomizer.authenticationEntryPoint(
                CustomAuthenticationEntryPoint(mapper)
            ) }
            .formLogin { customizer -> customizer.disable() }
            .httpBasic { customizer -> customizer.disable() }
            .csrf { customizer -> customizer.disable() }
            .cors{ customizer -> customizer.disable() }

        http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}