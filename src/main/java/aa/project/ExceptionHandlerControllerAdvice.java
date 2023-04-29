package aa.project;

import aa.project.exception.account.CustomException;
import aa.project.exception.account.CustomExceptionResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponseEntity> toAccountExceptionResponse(CustomException customException) {
        return CustomExceptionResponseEntity.toAccountExceptionResponse(customException.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> responseRequestBodyValidation(MethodArgumentNotValidException e) {
        System.out.println("asdasdasd");
        Map<String, String> error = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> error.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> responseRequestParamValidation(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
