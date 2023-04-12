package com.project.springbootwebstore.security;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests().antMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()
                .and()
                .authorizeRequests().antMatchers(
                        "/main/account/**",
                        "/main/cart/**",
                        "/main/favorites/**"
                ).authenticated()
                .and()
//                .formLogin(withDefaults())
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                .build();

    }

    @Bean
    public UserDetailsService users() {
        UserDetails userDetails = User.builder()
                .username("u")
                .password("{noop}p")
                .authorities("ROLE USER")
                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
        return new InMemoryUserDetailsManager(userDetails);
    }

}