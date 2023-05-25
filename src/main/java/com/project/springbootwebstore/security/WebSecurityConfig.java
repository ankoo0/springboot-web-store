package com.project.springbootwebstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    @Bean
//    @Primary
////    @Qualifier("customDataSource")
//    DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .url("jdbc:postgresql://localhost:5432/springboot-web-store")
//                .username("postgres")
//                .password("admin").build();
//    }

////    private final CustomUserDetailsService userDetailsService;
//    private final DataSource dataSource;
//    @Autowired
//    public WebSecurityConfig( DataSource dataSource) {
////        this.userDetailsService = userDetailsService;
//        this.dataSource=dataSource;
//        System.out.println(dataSource.toString());
//    }

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
                        "/main/favorites/**"
                ).authenticated()
                .and()
//                .formLogin(withDefaults())
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                .logout().logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
//                .deleteCookies()
                .logoutSuccessUrl("/main")
                .and()
                .authenticationProvider(authenticationProvider())
                .build();

    }


    @Bean
   protected UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

//    @Bean
//    @Primary
//    public DataSource primaryDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/springboot-web-store");
//        dataSourceBuilder.username("postgres");
//        dataSourceBuilder.password("admin");
//        return dataSourceBuilder.build();
//    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource customDataSource)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(customDataSource)
//                .usersByUsernameQuery("select username,password,enabled "
//                        + "from _user "
//                        + "where username = ?")
//                .authoritiesByUsernameQuery("select username,role "
//                        + "from _user "
//                        + "where username = ?");
//    }

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

//    @Bean
//    public UserDetailsService users() {
//        UserDetails userDetails = User.builder()
//                .username("u")
//                .password("{noop}p")
//                .authorities("ROLE USER")
//                .build();
////        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
////        users.createUser(user);
//        return new InMemoryUserDetailsManager(userDetails);
//    }

}