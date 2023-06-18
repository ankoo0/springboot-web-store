package com.project.springbootwebstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable().cors().
                and()
                .authorizeRequests().antMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/main/filter/**"
                ).permitAll()
                .and()
                .authorizeRequests().antMatchers(
                        "/main/account/**",
                        "/main/cart/**",
                        "/main/favorites/**",
                        "/admin/**"
                ).authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .defaultSuccessUrl("/")
                .successHandler(new RefererAuthenticationSuccessHandler())
                .and()
                .logout().logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/main")
                .and()
                .authenticationProvider(authenticationProvider())
                .build();

    }


    private static class RefererAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            String referer = request.getHeader("Referer");
            if (referer != null) {
                getRedirectStrategy().sendRedirect(request, response, referer);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }


    @Bean
   protected UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }


    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    protected DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

}