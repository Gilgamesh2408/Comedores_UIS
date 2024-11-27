package com.uis.ComedoresUIS.config;

import com.uis.ComedoresUIS.auth.filters.JwtTokenValidator;
import com.uis.ComedoresUIS.auth.services.CustomerUserDetailsService;
import com.uis.ComedoresUIS.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {

                    //public endpoints
                    http.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();

                    http.requestMatchers(HttpMethod.GET, "/student/**").hasRole("USER");
                    http.requestMatchers(HttpMethod.POST, "student/**").hasRole("USER");

                    http.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("ADMIN", "SUPERADMIN");
                    http.requestMatchers(HttpMethod.POST, "/admin/create/programming").hasAnyRole("ADMIN", "SUPERADMIN");
                    http.requestMatchers(HttpMethod.POST, "/admin/create/menu").hasAnyRole("ADMIN", "SUPERADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/admin/menus/{id}").hasAnyRole("ADMIN", "SUPERADMIN");

                    //Endpoints super admin
                    http.requestMatchers(HttpMethod.POST, "/admin/create/period").hasRole("SUPERADMIN");
                    http.requestMatchers(HttpMethod.POST, "/admin/create/ingredient").hasRole("SUPERADMIN");


                    http.anyRequest().authenticated();

                })
                .addFilterBefore(new JwtTokenValidator(jwtUtil), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomerUserDetailsService detailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(detailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
