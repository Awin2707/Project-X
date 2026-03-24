package com.spring.backend.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.backend.JWT.JwtAuth;
import com.spring.backend.UserDetails.CustomUserDetails;

@Configuration
public class Config {
    
    @Autowired private CustomUserDetails userDetails;
    @Autowired private JwtAuth auth;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http.csrf((csrf) -> csrf.disable()
            ).cors((cors) -> cors.configurationSource(configurationSource())
            ).formLogin((form) -> form.disable())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
.authorizeHttpRequests((auth) -> auth
    .requestMatchers("/public/**").permitAll()
    .requestMatchers(org.springframework.http.HttpMethod.POST, "/public/**").permitAll() // ✅ ADD THIS
    .anyRequest().authenticated()
)                        .addFilterBefore(auth,UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedHeaders(List.of("*"));
        cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        cors.setAllowedOrigins(List.of("http://localhost:3000", "https://dev-saving-cost.netlify.app"));
        cors.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
        url.registerCorsConfiguration("/**", cors);
        return url;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetails);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
