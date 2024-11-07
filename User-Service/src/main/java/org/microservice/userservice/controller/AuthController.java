package org.microservice.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.userservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<Void> checkUserIsAuth (@PathVariable Long id) {
        authService.checkUserIsAuth(id);
        return ResponseEntity.ok().build();
    }
}
