package com.luke.blog.services;

import com.luke.blog.domain.dtos.LoginRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticaionService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
}
