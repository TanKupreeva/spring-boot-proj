package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                                authorize.requestMatchers("/content").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/register/**").permitAll()

                                        .requestMatchers("/person/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/person/new").hasRole("ADMIN")
                                        .requestMatchers("/person/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/person/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/report/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/report/new").hasRole("USER")
                                        .requestMatchers("/report/details/**").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/report/search_list").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/report/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/grs/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/grs/new").hasRole("ADMIN")
                                        .requestMatchers("/grs/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/grs/delete/**").hasRole("ADMIN")
                                        .requestMatchers("/grs/search_list").hasAnyRole("ADMIN", "USER")


                                        .requestMatchers("/client/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/client/new").hasRole("ADMIN")
                                        .requestMatchers("/client/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/client/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/instrument/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/instrument/search_list/**").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/instrument/new").hasRole("ADMIN")
                                        .requestMatchers("/instrument/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/instrument/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/condition/new/**").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/condition/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/condition/delete/**").hasRole("ADMIN")
                                        .requestMatchers("/condition/all/**").hasRole("ADMIN")

                                        .requestMatchers("/criteria/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/criteria/new").hasRole("ADMIN")
                                        .requestMatchers("/criteria/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/criteria/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/umg/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/umg/new").hasRole("ADMIN")
                                        .requestMatchers("/umg/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/umg/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/office/all").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/office/new").hasRole("ADMIN")
                                        .requestMatchers("/office/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/office/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/result/all/**").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/result/new/**").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/result/edit/**").hasRole("ADMIN")
                                        .requestMatchers("/result/delete/**").hasRole("ADMIN")

                                        .requestMatchers("/detail_results/**").hasAnyRole("ADMIN", "USER")

                                        .requestMatchers("/css/**").permitAll()
                                        .requestMatchers("/js/**").permitAll()
                                        .requestMatchers("/img/**").permitAll()

                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/content")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}