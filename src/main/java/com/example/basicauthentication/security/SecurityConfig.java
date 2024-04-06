package com.example.basicauthentication.security;

import com.example.basicauthentication.entity.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   HandlerMappingIntrospector introspector)throws Exception{
        MvcRequestMatcher.Builder mvcRequestBuilder=new MvcRequestMatcher.Builder(introspector);
        httpSecurity.headers(x->
                x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.
                                ignoringRequestMatchers(mvcRequestBuilder.pattern("/public/**"))
                                .ignoringRequestMatchers(PathRequest.toH2Console()))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.
                                requestMatchers(mvcRequestBuilder.pattern("/public/**")).permitAll()
                                .requestMatchers(mvcRequestBuilder.pattern("/admin/**")).hasRole(Role.ADMIN.getValue())
                                .requestMatchers(mvcRequestBuilder.pattern("/user/**")).hasRole(Role.USER.getValue())
                                .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
                return httpSecurity.build();





    }
}
