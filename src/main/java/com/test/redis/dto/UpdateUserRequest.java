package com.test.redis.dto;

public record UpdateUserRequest(
        Long id,
        String password
) {
}
