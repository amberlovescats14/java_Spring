package com.example.test;

import com.example.test.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader){
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //set login page and redirect
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/users")
                    .permitAll()
                .and()
//                on logout
                    .logout()
                    .logoutSuccessUrl("/login?logout")
                .and()
//                allows anyone to see home and ads
                    .authorizeRequests()
                    .antMatchers("/", "/posts")
                    .permitAll()
                .and()
//                protected routes
                    .authorizeRequests()
                .antMatchers(
                        "/categories",
                        "/posts/create",
                        "/posts/edit/{id}",
                        "/posts"
                )
                .authenticated()
                ;
    }

//! WILD CARD    /**
}
