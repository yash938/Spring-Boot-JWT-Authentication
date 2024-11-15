package com.JWT.JWT.Authnetication.Config;

import com.JWT.JWT.Authnetication.Security.AuthEntryPoint;
import com.JWT.JWT.Authnetication.Security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private JwtAuthFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedMethods(List.of("*"));
                        configuration.setAllowedHeaders(List.of("*"));
                        configuration.setMaxAge(4000L);
                        return configuration;
                    }
                }))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/user/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/product/**").authenticated()
                                .requestMatchers("/product/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/product/**").hasRole("ADMIN")
                                .requestMatchers("/order/**").hasAnyRole("ADMIN","NORMAL")
                                .requestMatchers("/order/**").hasRole("ADMIN")
                                .requestMatchers("/category/create").hasRole("ADMIN")
                                .requestMatchers("/auth/generateToken").permitAll()
                                .requestMatchers("/auth/**").authenticated()
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**").permitAll()

                                .anyRequest().permitAll()


                );
        httpSecurity.exceptionHandling(ex ->ex.authenticationEntryPoint(authEntryPoint));
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
