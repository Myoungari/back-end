package myongari.backend.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> noSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(404)
                .body(new Error(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(400)
                .body(new Error(e.getMessage()));
    }
}
