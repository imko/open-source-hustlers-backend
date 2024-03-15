package com.opensourcehustlers.opensourcehustlersbackend.web.auth;

import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.InvalidTokenException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.InvalidUserAuthorityException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.InvalidUserCredentialsException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserAlreadyExistsException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationControllerAdvice {

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex) {
    return toErrorMap(ex);
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> userNotFoundExceptionHandler(UserNotFoundException ex) {
    return toErrorMap(ex);
  }

  @ExceptionHandler(InvalidUserAuthorityException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> invalidUserAuthorityExceptionHandler(
      InvalidUserAuthorityException ex) {
    return toErrorMap(ex);
  }

  @ExceptionHandler(InvalidUserCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Map<String, String> invalidUserCredentialsExceptionHandler(
      InvalidUserCredentialsException ex) {
    return toErrorMap(ex);
  }

  @ExceptionHandler(InvalidTokenException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Map<String, String> invalidTokenExceptionHandler(InvalidTokenException ex) {
    return toErrorMap(ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> methodArgumentNotValidExceptionHandler(
      MethodArgumentNotValidException ex) {
    /* Handles validation errors for objects. */
    var errors = new HashMap<String, String>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return errors;
  }

  private Map<String, String> toErrorMap(Exception ex) {
    var errors = new HashMap<String, String>();
    errors.put("message", ex.getMessage());
    return errors;
  }
}
