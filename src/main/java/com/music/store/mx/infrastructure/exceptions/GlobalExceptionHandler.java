package com.music.store.mx.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.music.store.mx.application.controller.MusicStoreController;
import com.music.store.mx.application.controller.MusicStoreInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

  
  @ExceptionHandler({MusicStoreInputException.class})
  public ResponseEntity<ErrorResponse> badRequest(
      MusicStoreInputException musicStoreInputException) {
    return new ResponseEntity<>(new ErrorResponse(1001, musicStoreInputException.getMessage(),
        "input validation", "reenvie la petición con valores de inversión correctos"),
        HttpStatus.BAD_REQUEST);
  }
  
  
  /**
   * Internal server exception.
   *
   * @param serverException the server exception
   * @return the response entity
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorResponse> internalServerException(
      Exception serverException) {
    return new ResponseEntity<>(new ErrorResponse(9999, serverException.getMessage(),
        "error interno", null),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}