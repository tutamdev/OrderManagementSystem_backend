package com.group19.OrderManagementSystem_backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.private-key}")
    private String SIGNER_KEY;

    @Autowired
    private CustomJwtDecoder customJwtDecoder;

    private final String[] PUBLIC_ENDPOINT = {
            "/employees", "/auth/**",
    };

    private final String[] ADMIN_ENDPOINT = {
            "/employees"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Tắt cấu hình csrf
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // Cấu hình endpoint
        httpSecurity.authorizeRequests(requests -> requests
                        // Cấu hình các endpoint không cần xác thực
                        .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()

                        // Chỉ cho phép role EMPLOYEE truy cập các endpoint

                        // Chỉ cho phép role ADMIN truy cập các endpoint

                        // Các request còn lại cần phải xác thực
                        .anyRequest().authenticated()
                );

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwtConfigurer -> jwtConfigurer
                                        .decoder(customJwtDecoder)
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                // bắt lỗi 401 vì không thể truy cập đến tầng GlobalException
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                // Từ chối truy cập
                .accessDeniedHandler(new JwtAuthenticationEntryPoint())
        );

        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return converter;
    }
}