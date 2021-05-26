package com.sapient.football.manager.exception;

import com.sapient.football.manager.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

  private final ErrorCode error;

  public ServiceException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.error = errorCode;
  }
}
