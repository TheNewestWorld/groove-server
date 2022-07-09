package org.bogus.groove_auth.endpoint.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.UserInfo;
import org.bogus.groove_auth.domain.user.UserService;
import org.bogus.groove_auth.endpoint.auth.RegisterRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users/register")
    public CommonResponse<UserInfo> register(@RequestBody RegisterRequest request) {
        var result = userService.register(request.getEmail());
        return CommonResponse.success(result);
    }

    @GetMapping("/api/users/self")
    public CommonResponse<UserInfoGetResponse> getSelfInfo(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken
    ) {
        var result = userService.getSelf(accessToken);
        return CommonResponse.success(
            new UserInfoGetResponse(result.getId(), result.getEmail(), result.getType(), result.getAuthorities()));
    }
}
