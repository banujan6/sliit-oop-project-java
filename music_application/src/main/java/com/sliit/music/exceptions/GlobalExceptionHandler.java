package com.sliit.music.exceptions;
import com.sliit.music.dto.Constant;
import com.sliit.music.dto.ErrorResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MusicStoreException.class)
    public ErrorResponse handleDataException(MusicStoreException e) {
        return new ErrorResponse(
                Constant.VALIDATION_ERROR_CODE,
                e.getErrorMsg()
        );
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleException(Exception e) {
//        return new ErrorResponse(
//                Constant.ERROR_CODE,
//                Constant.INTERNAL_SERVER_ERROR_EXCEPTION
//        );
//    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullPointerException(NullPointerException e) {
        return new ErrorResponse(Constant.VALIDATION_ERROR_CODE,
            Constant.NULL_POINTER_EXCEPTION);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullPointerException(SQLIntegrityConstraintViolationException e) {
        return new ErrorResponse(Constant.VALIDATION_ERROR_CODE,
            e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullPointerException(ConstraintViolationException e) {
        return new ErrorResponse(Constant.VALIDATION_ERROR_CODE,
            e.getMessage());
    }
}
