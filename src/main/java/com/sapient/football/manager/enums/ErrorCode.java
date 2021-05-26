package com.sapient.football.manager.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

  E1("Internal Server Error");

  private final String message;
  private final String code;

  ErrorCode(String message) {
    this.code = this.name();
    this.message = message;
  }

}
