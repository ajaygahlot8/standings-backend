package com.sapient.football.manager.exception;

import com.sapient.football.manager.enums.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidDataException extends ServiceException {

  public InvalidDataException(ErrorCode errorCode) {
    super(errorCode);
  }
}
