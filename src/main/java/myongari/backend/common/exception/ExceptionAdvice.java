package myongari.backend.common.exception;

import lombok.extern.slf4j.Slf4j;
import myongari.backend.common.response.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> handleNoSuchElementException(NoSuchElementException e) {
        log.warn("handleNoSuchElementException", e);
        return ResponseEntity.status(404)
                .body(Error.of(404, e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("handleIllegalArgumentException", e);
        return ResponseEntity.status(400)
                .body(Error.of(400, e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("handleNoResourceFoundException", ex);
        return ResponseEntity.status(400)
                .body(Error.of(400, "잘못된 요청입니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleAllException(Exception e) {
        log.warn("handleAllException", e);
        return ResponseEntity.status(500)
                .body(Error.of(500, "서버 내부에 오류가 발생했습니다."));
    }
}
