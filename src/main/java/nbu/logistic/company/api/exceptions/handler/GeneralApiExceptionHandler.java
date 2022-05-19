package nbu.logistic.company.api.exceptions.handler;

import nbu.logistic.company.api.exceptions.GeneralApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(value = GeneralApiException.class)
    public ErrorResponseDto handleApiException(RuntimeException runtimeException) {
        return ErrorResponseDto
                .builder()
                .message(runtimeException.getMessage())
                .build();
    }
}
