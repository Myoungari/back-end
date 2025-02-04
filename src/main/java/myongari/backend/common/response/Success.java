package myongari.backend.common.response;

public record Success<T>(int code, T data) {

    public static <T> Success<T> of(int code, T data) {
        return new Success<>(code, data);
    }
}
