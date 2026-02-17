package com.npci.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.npci.transaction.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
