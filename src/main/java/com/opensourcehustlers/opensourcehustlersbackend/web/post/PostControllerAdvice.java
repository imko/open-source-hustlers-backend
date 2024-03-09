package com.opensourcehustlers.opensourcehustlersbackend.web.post;

import com.opensourcehustlers.opensourcehustlersbackend.exception.post.PostNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostControllerAdvice {

  @ExceptionHandler(PostNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> postNotFoundExceptionHandler(PostNotFoundException ex) {
    return toErrorMap(ex);
  }

  private Map<String, String> toErrorMap(Exception ex) {
    var errors = new HashMap<String, String>();
    errors.put("message", ex.getMessage());
    return errors;
  }
}
