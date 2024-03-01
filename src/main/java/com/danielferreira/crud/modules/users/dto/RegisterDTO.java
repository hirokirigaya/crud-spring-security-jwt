package com.danielferreira.crud.modules.users.dto;

import com.danielferreira.crud.modules.users.enums.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role) {
}
