package backend.properties_crud.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  /* spring security related exceptions are to be defined and registered with
   * configuration for being in effect like customAuthenticationEntryPoint for
   * unauthorized users thus here only spring boot and ecosystem related
   * exceptions are defined.
   */

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handle_request_body_not_valid_exception(
      MethodArgumentNotValidException exception) {
    Map<String, Object> response = new HashMap<>();
    response.put("message", exception.getDetailMessageArguments()[1]);
    return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}