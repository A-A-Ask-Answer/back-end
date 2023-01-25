package a_a.project;

import a_a.project.exception.user.UserException;
import a_a.project.exception.user.UserExceptionResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserExceptionResponseEntity> toUserExceptionResponse(UserException userException) {
        return UserExceptionResponseEntity.toUserExceptionResponse(userException.getUserErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> responseValidation(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> error.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(error);
    }


}
