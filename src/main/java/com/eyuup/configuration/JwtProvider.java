package com.eyuup.configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
        static SecretKey key=Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

        public String generateToken(Authentication authentication){

            Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();


            String roles=populateAuthorities(authorities);
            
            return Jwts.builder()
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime()+86400000))
            .claim("authorities", roles)
            .claim("email", authentication.getName())
            .signWith(key)
            .compact();
        }

        
        public String getEmailFromJwtToken(String Token){
            String jwt=Token.substring(7);
             Claims claims=Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
          String email=String.valueOf(claims.get("email"));
          return email;
        
        }


        private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
            Set<String> auths=new HashSet<>();

            for(GrantedAuthority authority :authorities){
                auths.add(authority.getAuthority());

            }
            return String.join(",", auths);
        }
}
