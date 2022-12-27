package com.turing.exception;

import com.google.common.base.Throwables;
import com.turing.model.response.OperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @ControllerAdvice tells your spring application, that this class will do the exception handling for your application.
 * @ExceptionHandler annotation to define the class of Exception it will catch. (A Base class will catch all the Inherited and extended classes)
 *
 * @author Ganajan
 * @version 1.0
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<OperationResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.error("Exception occurred: ", Throwables.getRootCause(ex));
        OperationResponse resp = new OperationResponse();
        resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
        resp.setOperationMessage(Throwables.getRootCause(ex).getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<OperationResponse> handleBaseException(Exception ex, WebRequest request) {
        logger.error("Exception occurred: ", Throwables.getRootCause(ex));
        OperationResponse resp = new OperationResponse();
        resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
        resp.setOperationMessage(Throwables.getRootCause(ex).getMessage());
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
