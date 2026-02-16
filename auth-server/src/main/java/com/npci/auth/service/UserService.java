package com.npci.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.npci.auth.entity.AppUser;
import com.npci.auth.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public AppUser register(AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
