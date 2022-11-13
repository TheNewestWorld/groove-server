package org.bogus.groove.endpoint.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.bogus.groove.domain.user.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "회원가입")
    @PostMapping("/api/users/register")
    public CommonResponse<Void> register(@RequestBody UserRegisterRequest request) {
        userService.register(
            new UserRegisterParam(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getNickname()
            )
        );
        return CommonResponse.success();
    }

    @Secured({SecurityCode.INACTIVE, SecurityCode.USER})
    @Operation(summary = "로그인 유저 정보 조회")
    @GetMapping("/api/users/self")
    public CommonResponse<UserInfoGetResponse> getSelfInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var result = userService.getUserInfo(userDetails.getUserId());
        return CommonResponse.success(
            new UserInfoGetResponse(
                result.getId(),
                result.getEmail(),
                result.getType(),
                result.getAuthorities()
            )
        );
    }

    @Secured(SecurityCode.INACTIVE)
    @Operation(summary = "유저 인증 메일 재발송 (로그인 한 경우)")
    @PostMapping("/api/mail/authentication")
    public CommonResponse<Void> sendEmailAuthenticationLink(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.sendAuthenticationMail(userDetails.getEmail());
        return CommonResponse.success();
    }

    @Operation(summary = "비밀번호 변경 메일 요청")
    @PostMapping("/api/mail/change-password")
    public CommonResponse<Void> sendPasswordUpdateLink(@RequestBody PasswordUpdateLinkRequest request) {
        userService.sendPasswordUpdateLink(request.getEmail());
        return CommonResponse.success();
    }

    @Operation(summary = "비밀번호 변경")
    @PutMapping("/api/users/password")
    public CommonResponse<Void> updatePassword(@RequestBody PasswordChangeRequest request) {
        userService.updatePassword(request.getSessionKey(), request.getPassword());
        return CommonResponse.success();
    }
}
