package com.usman.forum.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Slf4j
@AllArgsConstructor
public class JwtFilter  extends OncePerRequestFilter {
    private  final JwtUtil jwtUtil;
    private  final UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authentication");
        final String jwt;
        final String userEmail;
        log.info("we are starting1"+authHeader);
        if(authHeader==null|| !authHeader.startsWith("Bearer ") ){
            filterChain.doFilter(request,response);
            log.info("we are starting2");
            return;
        }
        log.info("we are starting3");
        jwt=authHeader.substring(7);
        log.info("we are starting4"+jwt);
        userEmail= jwtUtil.extractUserName(jwt);
        log.info(userEmail+"-------------");
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtUtil.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken passwordAuthenticationToken
                        =new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()

                );
                passwordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)

                );
                SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);

            }
            filterChain.doFilter(request,response);
        }

    }
}
