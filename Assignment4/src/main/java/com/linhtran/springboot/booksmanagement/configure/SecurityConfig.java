package com.linhtran.springboot.booksmanagement.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/dashboard", "/api/books",
                            "/book/detail/**", "/api/all-comments/").permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/admin/*").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                    .antMatchers("/login**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/signin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/login-success")
                    .permitAll()
                    .failureUrl("/login-failure")
                .and()
                .logout()
                    .logoutUrl("/signout")
                    .logoutSuccessUrl("/").permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/css/**",
                        "/register", "/resources/img/**",
                        "/resources/js/**", "/resources/upload/**");
    }
}
