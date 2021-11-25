package com.ironhack.sporttracker.authservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.sporttracker.authservice.dto.LoginDTO;
import com.ironhack.sporttracker.authservice.dto.UserDetailsDTO;
import com.ironhack.sporttracker.authservice.service.UserEntityService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserEntityService userEntityService;
    private Environment environment;

    public AuthenticationFilter(UserEntityService userEntityService, Environment environment, AuthenticationManager authenticationManager) {
        this.userEntityService = userEntityService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDTO creds = new ObjectMapper()
                    .readValue(request.getInputStream(),LoginDTO.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getLogin(),creds.getPassword(),new ArrayList<>()));

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String login = ((User)authResult.getPrincipal()).getUsername();
        UserDetailsDTO userDetailsDTO = userEntityService.getUserDetailsByLogin(login);

        String token = Jwts.builder()
                .setSubject(userDetailsDTO.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
                .compact();

        response.addHeader("token",token);
        response.setHeader("pragma",token);
        response.addHeader("userId",userDetailsDTO.getId().toString());
    }
}
