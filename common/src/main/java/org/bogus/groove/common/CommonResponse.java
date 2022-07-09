package org.bogus.groove.common;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    private CommonResponseType result;
    private T data;
    private CommonResponseError error;

    public static CommonResponse<Void> success() {
        return new CommonResponse<>(CommonResponseType.SUCCESS, null, null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(CommonResponseType.SUCCESS, data, null);
    }

    public static CommonResponse<Void> error(String errorCode, String errorMessage) {
        return new CommonResponse<>(CommonResponseType.ERROR, null, new CommonResponseError(errorCode, errorMessage, null));
    }

    public static CommonResponse<Void> error(String errorCode, String errorMessage, Map<String, Object> extraData) {
        return new CommonResponse<>(CommonResponseType.ERROR, null, new CommonResponseError(errorCode, errorMessage, extraData));
    }
}
