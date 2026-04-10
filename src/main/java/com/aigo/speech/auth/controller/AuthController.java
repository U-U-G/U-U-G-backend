package com.aigo.speech.auth.controller;

import com.aigo.speech.auth.dto.EmailVerificationConfirmRequest;
import com.aigo.speech.auth.dto.EmailVerificationRequest;
import com.aigo.speech.global.dto.ApiResponse;
import com.aigo.speech.mail.service.MailVerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MailVerificationService mailVerificationService;

    // 인증 코드 발송
    @PostMapping("/email-verifications")
    public ResponseEntity<ApiResponse<Void>> sendVerificationCode(
            @RequestBody @Valid EmailVerificationRequest request
    ) {
        mailVerificationService.sendVerificationCode(request.email());
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 인증 코드 검증
    @PostMapping("/email-verifications/verify")
    public ResponseEntity<ApiResponse<Void>> verifyCode(
            @RequestBody @Valid EmailVerificationConfirmRequest request
    ) {
        mailVerificationService.verifyCode(
                request.email(),
                request.code()
        );

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
