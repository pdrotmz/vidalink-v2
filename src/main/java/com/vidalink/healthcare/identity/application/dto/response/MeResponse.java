package com.vidalink.healthcare.identity.application.dto.response;

import com.vidalink.healthcare.identity.domain.enums.UserRole;

import java.util.UUID;

public record MeResponse(
        UUID id,
        String name,
        String email,
        UserRole role
) {
}
