package org.bogus.groove.endpoint.auth;

import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.user.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Secured({SecurityCode.INACTIVE, SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @Operation(summary = "액세스 토큰 리프레쉬")
    @PostMapping("/api/auth/refresh")
    public CommonResponse<TokenRefreshResponse> refresh(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody TokenRefreshRequest request
    ) {
        var result = authService.refresh(userDetails.getUserId(), request.getRefreshToken());
        return CommonResponse.success(new TokenRefreshResponse(result));
    }

    @Secured({SecurityCode.INACTIVE, SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @Operation(summary = "로그아웃")
    @PostMapping("/api/auth/logout")
    public CommonResponse<Void> logout(HttpServletRequest request) {
        var accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        authService.logout(accessToken);
        return CommonResponse.success();
    }

    // Swagger 를 위한 껍데기 endpoint 임 실제 로그인 요청은 필터에서 처리되고 끝남
    @Operation(summary = "로그인")
    @PostMapping("/api/auth/login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return CommonResponse.success(
            new LoginResponse("", "")
        );
    }

    @PostMapping("/api/auth/email-authentications/{sessionKey}")
    public CommonResponse<Void> authenticateEmail(@PathVariable String sessionKey) {
        authService.authenticateEmail(sessionKey);
        return CommonResponse.success();
    }
}
