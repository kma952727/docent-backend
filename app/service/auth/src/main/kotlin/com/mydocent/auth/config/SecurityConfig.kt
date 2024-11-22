package com.mydocent.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * permitAll(): securityContextHolder에 인증 객체가 없어도 인증 실패처리되지 않음
 *
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val principalOauth2UserService :PrincipalOauth2UserService,
    private val oAuth2SuccessHandler: OAuth2SuccessHandler,
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .sessionManagement { sessionCustomizer -> sessionCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authCustomizer ->
                authCustomizer
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/free-users").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth2Customizer ->
                oauth2Customizer.userInfoEndpoint { userInfoEndpointCustomizer -> userInfoEndpointCustomizer.userService(principalOauth2UserService) }
                oauth2Customizer.successHandler(oAuth2SuccessHandler)
            }
            .exceptionHandling { exceptionCustomizer -> exceptionCustomizer.authenticationEntryPoint(CustomAuthenticationEntryPoint()) }
            .formLogin { customizer -> customizer.disable() }
            .httpBasic { customizer -> customizer.disable() }
            .csrf { customizer -> customizer.disable() }
//            .cors{ customizer -> customizer.disable() }
            .cors{ it.configurationSource(corsConfigurationSource()) }

        http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            addAllowedOrigin("*")
            addAllowedHeader("*")
            addAllowedMethod("*")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}