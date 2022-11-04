package org.bogus.groove.domain.user;

import java.io.InputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserProfileUpdateParam {
    private final InputStream inputStream;
    private final String fileName;
    private final long size;
}
