package com.sliit.music.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class MusicStoreException extends RuntimeException {
  private final String message;

  public MusicStoreException(String message) {
    super(message);
    this.message = message;
  }

  public String getErrorMsg() {
    return this.message;
  }

}
