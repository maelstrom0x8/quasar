package com.aeflheim.quasar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.aeflheim.quasar.config.filter.AuthenticationLoggingFilter;
import com.aeflheim.quasar.config.filter.RequestValidationFilter;

@Configuration
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/greet/hello").permitAll();
            auth.requestMatchers("/greet/**").authenticated();
            auth.requestMatchers(HttpMethod.GET, "/a").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/a").permitAll();
            auth.requestMatchers("/product/**").authenticated();
            auth.requestMatchers("/product/{code:^[0-9]*$}").permitAll();
            auth.anyRequest().denyAll();
        }).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .csrf().disable()
                .build();
    }

}
