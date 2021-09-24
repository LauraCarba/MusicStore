package com.music.store.mx.application.controller;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class MusicNotFaundException extends RuntimeException {
  
  public MusicNotFaundException() {

      super();

  }
}
