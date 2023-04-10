package com.usman.forum.config;

import com.usman.forum.service.Implementation.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class GeneralSecurity {

    private  JwtFilter jwtFilter;
    private  JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;
    private final static String [] USERPERMIT={
            "/api/questions/**","/api/answer/**","/api/subanswer/**",
            "/api/category/**",
};
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests(auth->
                auth.
                requestMatchers(HttpMethod.DELETE,"/api/user/**").hasAnyRole("ADMIN")

                        .requestMatchers("/api/user/**","/api/role/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE,USERPERMIT).hasAnyRole("ADMIN")
                        .requestMatchers("/api/questions/").hasAnyRole("USER")
                        .anyRequest().authenticated());


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic();
        http.formLogin();

    return http.build();

    }




    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(jwtUtil.passwordEncoder());
        return  daoAuthenticationProvider;
    }





    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}
