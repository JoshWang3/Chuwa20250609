package com.example.hw13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Allow only not logged-in (anonymous) users to access /anonymous* URLs.
        http.authorizeRequests()
                .antMatchers("/anonymous*")
                .anonymous();

        //Allow anyone (logged in or not) to access
        http.authorizeRequests()
                .antMatchers("/login*")
                .permitAll();
        //All other requests need the user to be logged in.
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();//	Enables basic auth (like what Postman uses).
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}pass")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}