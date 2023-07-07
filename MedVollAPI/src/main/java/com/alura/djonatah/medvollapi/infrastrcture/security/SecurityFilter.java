package com.alura.djonatah.medvollapi.infrastrcture.security;

import com.alura.djonatah.medvollapi.domain.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwtToken = getJWTToken(request);
        if(jwtToken != null){
            var subject = tokenService.getSubject(jwtToken);
            var user = userRepository.findByUsername(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
    public String getJWTToken(HttpServletRequest request){
        var jwtToken = request.getHeader("authorization");

        if(jwtToken != null) {
            jwtToken = jwtToken.replace("Bearer", "");
            jwtToken = jwtToken.trim();
        }

        return jwtToken;
    }
}
