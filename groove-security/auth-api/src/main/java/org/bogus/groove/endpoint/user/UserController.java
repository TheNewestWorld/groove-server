package org.bogus.groove.endpoint.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.config.CustomUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.user.UserInfo;
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

    @PostMapping("/api/users/register")
    public CommonResponse<UserInfo> register(@RequestBody RegisterRequest request) {
        var result = userService.register(
            new UserRegisterParam(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
            )
        );
        return CommonResponse.success(result);
    }

    @Secured(SecurityCode.USER)
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

    @PostMapping("/api/users/password-update-link/send")
    public CommonResponse<Void> sendPasswordUpdateLink(@RequestBody PasswordUpdateLinkRequest request) {
        userService.sendPasswordUpdateLink(request.getEmail());
        return CommonResponse.success();
    }

    @PutMapping("/api/users/password")
    public CommonResponse<Void> updatePassword(@RequestBody PasswordChangeRequest request) {
        userService.updatePassword(request.getSessionKey(), request.getPassword());
        return CommonResponse.success();
    }
}
