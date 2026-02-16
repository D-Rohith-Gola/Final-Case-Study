package com.npci.auth.controller;

import org.springframework.web.bind.annotation.*;
import com.npci.auth.entity.AppUser;
import com.npci.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        return service.register(user);
    }
}
