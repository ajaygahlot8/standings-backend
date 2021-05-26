package com.sapient.football.manager.exception;

import com.sapient.football.manager.enums.ErrorCode;
import lombok.Getter;

@Getter
public class StandingException extends ServiceException {

  public StandingException(ErrorCode errorCode) {
    super(errorCode);
  }
}
