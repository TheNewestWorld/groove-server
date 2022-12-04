package org.bogus.groove.endpoint.mail;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.endpoint.user.PasswordUpdateLinkRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @Secured(SecurityCode.INACTIVE)
    @Operation(summary = "유저 인증 메일 재발송 (로그인 한 경우)")
    @PostMapping("/api/mail/authentication")
    public CommonResponse<Void> sendEmailAuthenticationLink(@AuthenticationPrincipal CustomUserDetails userDetails) {
        mailService.sendAuthenticationMail(userDetails.getEmail());
        return CommonResponse.success();
    }

    @Operation(summary = "비밀번호 변경 메일 요청")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @PostMapping("/api/mail/change-password")
    public CommonResponse<Void> sendPasswordUpdateLink(@RequestBody PasswordUpdateLinkRequest request) {
        mailService.sendPasswordUpdateLink(request.getEmail());
        return CommonResponse.success();
    }
}
