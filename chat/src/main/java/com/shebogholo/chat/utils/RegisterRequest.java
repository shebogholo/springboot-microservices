package com.shebogholo.chat.utils;

public record RegisterRequest(
        String username,
        String password
) {}