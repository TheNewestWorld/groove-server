package org.bogus.groove.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentType {
    PROFILE("profile", true),
    // TODO 프론트에서 파일 다운로드 시 헤더에 토큰 넣는게 힘든가봄 가능할 것 같은뒤,, 일단 패스하기로 함 여유될 때 확인
    PRIVATE_RECORD("private-record", true),
    POST_IMAGE("post-image", true),
    POST_RECORD("post-record", true),
    INQUIRY_IMAGE("inquiry-image", true)
    ;

    private final String path;
    private final boolean preAuthorized;
}
