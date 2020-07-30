package com.expense.io.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Configuration(value = "settings")
public class Settings {

    private final List<String> skipPaths = List.of("/user/**", "/actuator/**");

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AntPathMatcher pathMatcher() {
        return new AntPathMatcher();
    }

    public List<String> getSkipPaths() {
        return skipPaths;
    }

    public Object getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext()
                                                                   .getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getPrincipal();
        } else return null;
    }
}
