package myongari.backend.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Success<T> {

    private final int code;
    private final T data;

    public static <T> Success of(int code, T data) {
        return new Success(code, data);
    }
}
