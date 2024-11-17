package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Désactive la protection CSRF pour simplifier les tests
                .authorizeHttpRequests() // use authorizeRequests()
                .requestMatchers("/api/v1/**").permitAll() // autorise tous les endpoints sous /api/v1/
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/admin/login")
          .loginProcessingUrl("/login").defaultSuccessUrl("/home", true) .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();;

        return http.build();
    }
}
