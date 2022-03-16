package com.etstur.controller;

import com.etstur.config.security.JwtTokenManager;
import com.etstur.dto.request.DoLoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class UserController {
    private final JwtTokenManager manager;

    @PostMapping("/token")
    @Operation(summary = "'admin' kullanıcısı için JWT token üretir.")
    public ResponseEntity<String> generateToken(@RequestBody @Valid DoLoginRequestDto dto) throws IllegalAccessException {
        if(dto.getUsername().equals("admin")) {
            String token = manager.createToken(dto.getUsername()).get();
            return ResponseEntity.ok().body(token);
        } else {
            throw new IllegalAccessException("Kullanıcı adı hatalı girildi");
        }
    }
}
