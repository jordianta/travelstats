package com.trebol.travelstats.config;

import com.trebol.travelstats.services.DBUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DBUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/static/**").permitAll()
            .anyRequest().authenticated()

            .and()
            .formLogin()
            .loginPage("/login")

            .and()
            .logout()
            .permitAll();

        httpSecurity.csrf().disable().cors();
        httpSecurity.headers().frameOptions().sameOrigin();

        return httpSecurity.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }
}