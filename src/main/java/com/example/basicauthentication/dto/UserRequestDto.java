package com.example.basicauthentication.dto;

import com.example.basicauthentication.entity.Role;
import lombok.Builder;

import java.util.Set;
@Builder
public record UserRequestDto(
        String name,
        String username,
        String password,
        Set<Role> authorities
) {
}
