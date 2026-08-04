package com.vidalink.healthcare.identity.presentation.controller;

import com.vidalink.healthcare.identity.application.dto.response.MeResponse;
import com.vidalink.healthcare.identity.application.usecase.GetCurrentUserUseCase;
import com.vidalink.healthcare.identity.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetCurrentUserUseCase getCurrentUserUseCase;

    public UserController(GetCurrentUserUseCase getCurrentUserUseCase) {
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public MeResponse me(@AuthenticationPrincipal User user) {
        return getCurrentUserUseCase.execute(user);
    }
}
