package com.usman.forum.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtUtil {
    private  static final  String SECRET_KEY=
            "2948404D635166546A576E5A7234753778217A25432A462D4A614E645267556B";


    public String extractUserName(String token) {

        return  extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token).getBody();


    }

    public  <T> T extractClaim(String token , Function<Claims,T> claimsTFunction){
        final Claims  claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);

    }

    private Key getSignKey() {
        byte [] bytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken( UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);


    }

    private String generateToken(Map<String ,Object> extralClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extralClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        final String userName=extractUserName(token);
        return  userName.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }


    private  Date extractExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
