package br.com.teste.demo.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(ex.getMessage());
        jsonResponse.setStatusCode(404);

        return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleError(
            Exception ex, WebRequest request) {

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(ex.getMessage());
        jsonResponse.setStatusCode(500);

        return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
