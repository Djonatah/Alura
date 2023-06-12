package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.user.User;
import com.alura.djonatah.medvollapi.domain.model.user.UserData;
import com.alura.djonatah.medvollapi.infrastrcture.security.JWTTokenData;
import com.alura.djonatah.medvollapi.infrastrcture.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserData userData){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        var encoded = encoder.encode(userData.password());
//        System.out.println(encoded);
        var authenticationToken = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());
        var authentication = manager.authenticate(authenticationToken);
        var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(jwtToken));
    }
}
