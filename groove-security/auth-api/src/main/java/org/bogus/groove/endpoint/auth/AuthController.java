package org.bogus.groove.endpoint.auth;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.user.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Secured(SecurityCode.USER)
    @PostMapping("/api/auth/refresh")
    public CommonResponse<TokenRefreshResponse> refresh(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody TokenRefreshRequest request
    ) {
        var result = authService.refresh(userDetails.getUserId(), request.getRefreshToken());
        return CommonResponse.success(new TokenRefreshResponse(result));
    }

    @Secured(SecurityCode.USER)
    @PostMapping("/api/auth/logout")
    public CommonResponse<Void> logout(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken
    ) {
        authService.logout(accessToken);
        return CommonResponse.success();
    }

    // Swagger 를 위한 껍데기 endpoint 임 실제 로그인 요청은 필터에서 처리되고 끝남
    @PostMapping("/api/auth/login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return CommonResponse.success(
            new LoginResponse("", "")
        );
    }
}
