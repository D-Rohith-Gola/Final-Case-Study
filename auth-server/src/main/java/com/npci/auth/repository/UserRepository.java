package com.npci.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.npci.auth.entity.AppUser;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
