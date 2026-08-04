package com.vidalink.healthcare.identity.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "name must be filled") String name,
        @NotBlank(message = "email must be filled") String email,
        @NotBlank(message = "password must be filled") @Size(min = 8) String password,
        @NotBlank(message = "CPF must be filled") String cpf
) {
}
