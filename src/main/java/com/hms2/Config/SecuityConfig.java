package com.hms2.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecuityConfig {
    private JwtFilter jwtFilter;

    public SecuityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        // h(cd)2
        http.csrf().disable().cors().disable();
        // happ security filter
         http.addFilterBefore(jwtFilter , AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/auth/sign-up",
//                        "/api/auth/login",
//                        "/api/auth/property/sign-up",
//                        "/api/v1/property/getproperty",
//                        "/api/v1/property/propertyId/{id}",
//               " /api/v1/property/{searchParam}"
//                        )
//                .permitAll()
//                .requestMatchers("/api/v1/property/addproperty")
//                .hasRole("OWNER")
//                .requestMatchers("/api/v1/property/deleteproperty",
//
//
//                        "/api/country/addcountry",
//                        "/api/city/addcity")
//                .hasAnyRole("OWNER","ADMIN")
//                .requestMatchers("/api/auth/blog/sign-up",
//                        "/api/v1/property/propertyupdate/{id}")
//                .hasRole("ADMIN")
//                .anyRequest().authenticated();

        return http.build();
    }
}
