package it.maelstrom.tabula.config.exception;

import it.maelstrom.tabula.web.exception.APIException;
import it.maelstrom.tabula.web.exception.APIExceptionResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({APIException.class})
    public ResponseEntity<APIExceptionResponse> handleApiExceptions(APIException ex) {
        ResponseStatus annotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        HttpStatus status = annotation == null
                ? HttpStatus.INTERNAL_SERVER_ERROR

                : annotation.value();
        APIExceptionResponse responseBody = APIExceptionResponse
                .builder()
                .message(ex.getMessage())
                .reason(ex.getReason())
                .metadata(ex.getMetadata())
                .build();

        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<APIExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                                error -> ((FieldError)error).getField(),
                        error -> error.getDefaultMessage()
                ));

        APIExceptionResponse responseBody = APIExceptionResponse
                .builder()
                .metadata(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<APIExceptionResponse> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, Object> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
        APIExceptionResponse responseBody = APIExceptionResponse
                .builder()
                .metadata(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
