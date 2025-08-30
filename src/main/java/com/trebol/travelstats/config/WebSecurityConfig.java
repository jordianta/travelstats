package com.trebol.travelstats.config;

import com.trebol.travelstats.services.DBUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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

        return httpSecurity.authorizeHttpRequests(auth -> auth
                                   .requestMatchers("/login").permitAll()
                                   .requestMatchers("/static/**").permitAll()
                                   .anyRequest().authenticated()
                           )
                           .formLogin(formLogin -> formLogin
                                   .loginPage("/login")
                                   .permitAll()
                           )
                           .csrf(AbstractHttpConfigurer::disable)
                           .cors(AbstractHttpConfigurer::disable)
                           .headers(headers ->
                                   headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                           .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }
}