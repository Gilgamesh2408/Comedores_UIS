package com.uis.ComedoresUIS.auth.controllers;

import com.uis.ComedoresUIS.auth.dto.AuthLoginRequest;
import com.uis.ComedoresUIS.auth.dto.AuthResponse;
import com.uis.ComedoresUIS.auth.services.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody AuthLoginRequest authLoginRequest) {
        return ResponseEntity.ok(customerUserDetailsService.login(authLoginRequest));
    }


}
