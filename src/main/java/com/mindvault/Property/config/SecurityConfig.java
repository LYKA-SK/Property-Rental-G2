package com.mindvault.Property.config;

import com.mindvault.Property.repositories.UserRepository;
import com.mindvault.Property.services.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public SecurityConfig(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                
                // --- ROLE-BASED ACCESS CONTROL ---
                // 1. Only Agents can perform Write/Update/Delete operations
                .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAuthority("ROLE_AGENT")
                .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasAuthority("ROLE_AGENT")
                .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAuthority("ROLE_AGENT")
                
                // 2. Both Users and Agents can view posts
                .requestMatchers(HttpMethod.GET, "/api/posts/**").hasAnyAuthority("ROLE_USER", "ROLE_AGENT")
                
                .requestMatchers("/api/auth/**").permitAll() // Allow registration/login
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            // Pass the beans into the filter correctly
            .addFilterBefore(new JwtAuthenticationFilter(userDetailsService(), jwtService), 
                             UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}