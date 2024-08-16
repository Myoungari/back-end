package myongari.backend.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Error {

    private final int code;
    private final String msg;

    public static Error of(int code, String msg) {
        return new Error(code, msg);
    }

}
