package com.luke.blog.services;

import com.luke.blog.domain.entity.User;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId);
}
