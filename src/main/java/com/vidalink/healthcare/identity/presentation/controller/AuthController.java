package com.vidalink.healthcare.identity.presentation.controller;

import com.vidalink.healthcare.identity.application.dto.request.LoginRequest;
import com.vidalink.healthcare.identity.application.dto.request.RegisterRequest;
import com.vidalink.healthcare.identity.application.dto.response.LoginResponse;
import com.vidalink.healthcare.identity.application.dto.response.MeResponse;
import com.vidalink.healthcare.identity.application.usecase.GetCurrentUserUseCase;
import com.vidalink.healthcare.identity.application.usecase.LoginUseCase;
import com.vidalink.healthcare.identity.application.usecase.RegisterUserUseCase;
import com.vidalink.healthcare.identity.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest request) {
        registerUserUseCase.execute(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return loginUseCase.execute(request);
    }
}
