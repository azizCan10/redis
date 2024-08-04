package com.test.redis.dto;

public record CreateUserRequest(
        String username,
        String password
) {
}
