package org.bogus.groove.endpoint.something;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove.domain.something.Something;
import org.bogus.groove.domain.something.SomethingService;
import org.bogus.groove.endpoint.middleware.Authorized;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SomethingController {
    private final SomethingService somethingService;

    @GetMapping("/api/somethings/{somethingId}")
    public CommonResponse<Something> getSomething(@PathVariable Long somethingId) {
        return CommonResponse.success(somethingService.get(somethingId));
    }

    @PostMapping("/api/somethings")
    public CommonResponse<Void> create(@RequestBody SomethingCreateRequest request) {
        somethingService.create(request.getName(), request.getAge());
        return CommonResponse.success();
    }

    @Authorized({Authority.USER})
    @PutMapping("/api/somethings/{somethingId}")
    public CommonResponse<Void> update(
        @RequestBody SomethingUpdateRequest request,
        @PathVariable Long somethingId
    ) {
        somethingService.updateAge(somethingId, request.getAge());
        return CommonResponse.success();
    }

    @Authorized({Authority.ADMIN})
    @DeleteMapping("/api/somethings/{somethingId}")
    public CommonResponse<Void> delete(@PathVariable Long somethingId) {
        somethingService.delete(somethingId);
        return CommonResponse.success();
    }
}