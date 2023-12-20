package com.thuan.pttk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceCustom();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(userDetailsService());
        return dao;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] resources = new String[] {
                "/css/**", "/img/**", "/js/**", "/lib/**", "/scss/**"
        };

        http.csrf(csrf -> csrf.disable());
        http.authenticationProvider(authenticationProvider());
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/cart/**").hasRole("CUSTOMER")
                        .requestMatchers("/checkout/**").hasRole("CUSTOMER")
                        .requestMatchers(resources).permitAll()
                        .anyRequest().permitAll())
                .formLogin(login -> login
                        .loginPage("/login-page").permitAll()
                        .loginProcessingUrl("/sign-in")
                        .failureUrl("/login-page?error").permitAll()
                        .defaultSuccessUrl("/login-success"))
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login-page?logout"))
                .exceptionHandling(e -> e.accessDeniedPage("/403").authenticationEntryPoint(
                        (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)));
        return http.build();
    }
}
