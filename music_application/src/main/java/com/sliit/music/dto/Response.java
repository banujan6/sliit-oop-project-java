package com.sliit.music.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
  private String statusCode;
  private String message;
  private Object data;

  public Response(String statusCode, String message, Object data) {
    this.statusCode = statusCode;
    this.message = message;
    this.data = data;
  }

  public Response(String statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }
}
