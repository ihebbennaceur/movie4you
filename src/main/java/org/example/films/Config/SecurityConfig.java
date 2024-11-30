package org.example.films.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable() // Disable CSRF for simplicity (only for testing)
                    .authorizeHttpRequests()
                    .anyRequest().permitAll() // Permit all requests
                    .and()
                    .httpBasic(); // Optional: Enable basic HTTP authentication

            return http.build();
    }
}


