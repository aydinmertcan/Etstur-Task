package com.etstur.controller;

import com.etstur.config.security.JwtTokenManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final JwtTokenManager manager;

    @GetMapping("/token")
    @Operation(summary = "'admin' kullanıcısı için JWT token üretir.")
    public ResponseEntity<String> generateToken() throws InvalidKeySpecException, NoSuchAlgorithmException {
        return ResponseEntity.ok().body(manager.createToken("admin").get());
    }
}
