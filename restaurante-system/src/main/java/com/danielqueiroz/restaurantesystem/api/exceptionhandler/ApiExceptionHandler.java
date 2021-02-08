package com.danielqueiroz.restaurantesystem.api.exceptionhandler;

import com.danielqueiroz.restaurantesystem.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemMessage problema = createProblemMessageBuilder(status, ProblemType.QUEBRA_NEGOCIO, ex.getMessage())
                .userMessage(ex.getMessage()).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    private ProblemMessage.ProblemMessageBuilder createProblemMessageBuilder(HttpStatus status, ProblemType problemType,
                                                                             String detail) {
        return ProblemMessage.builder().status(status.value()).type(problemType.getPath()).title(problemType.getTitle())
                .detail(detail).timestamp(OffsetDateTime.now());
    }
}
