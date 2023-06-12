package com.alura.djonatah.medvollapi.domain.dataaccess;

import com.alura.djonatah.medvollapi.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
