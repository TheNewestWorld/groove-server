package org.bogus.groove_auth.endpoint.auth;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove_auth.domain.user.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        var result = authService.login(request.getEmail());
        return CommonResponse.success(new LoginResponse(result.getAccessToken(), result.getRefreshToken()));
    }

    @PostMapping("/api/auth/refresh")
    public CommonResponse<TokenRefreshResponse> refresh(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken,
        @RequestBody TokenRefreshRequest request
    ) {
        var result = authService.refresh(accessToken, request.getRefreshToken());
        return CommonResponse.success(new TokenRefreshResponse(result));
    }

    @PostMapping("/api/auth/logout")
    public CommonResponse<Void> logout(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken
    ) {
        authService.logout(accessToken);
        return CommonResponse.success();
    }
}
