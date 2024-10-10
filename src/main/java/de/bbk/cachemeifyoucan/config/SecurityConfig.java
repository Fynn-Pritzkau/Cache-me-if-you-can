package de.bbk.cachemeifyoucan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig implements WebMvcConfigurer
{
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**").allowedOrigins("http://localhost:5173", "http://localhost:9191");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login", "/api/logout", "/api/checkAuth").permitAll()
                        .requestMatchers("/api/user").authenticated() // Protect user endpoint
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://localhost:5173/Welcome", true)) // Frontend URL
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .logoutSuccessUrl("/api/login"));

        return http.build();
    }
}