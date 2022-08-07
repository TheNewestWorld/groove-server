package org.bogus.groove_auth.endpoint.user;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove_auth.config.security.CustomUserDetails;
import org.bogus.groove_auth.domain.user.UserInfo;
import org.bogus.groove_auth.domain.user.UserService;
import org.bogus.groove_auth.domain.user.authority.Authority;
import org.bogus.groove_auth.endpoint.auth.RegisterRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users/register")
    public CommonResponse<UserInfo> register(@RequestBody RegisterRequest request) {
        var result = userService.register(request.toParam());
        return CommonResponse.success(result);
    }

    @Secured(Authority.SecurityCode.USER)
    @GetMapping("/api/users/self")
    public CommonResponse<UserInfoGetResponse> getSelfInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var result = userService.getUserInfo(userDetails.getUserId());
        return CommonResponse.success(
            new UserInfoGetResponse(
                result.getId(),
                result.getEmail(),
                result.getType().name(),
                result.getAuthorities().stream().map(Enum::name).collect(Collectors.toList())
            )
        );
    }
}
