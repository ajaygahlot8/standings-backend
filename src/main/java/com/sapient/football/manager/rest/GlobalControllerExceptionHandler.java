package com.sapient.football.manager.rest;

import com.sapient.football.manager.exception.InvalidDataException;
import com.sapient.football.manager.exception.ServiceException;
import com.sapient.football.manager.exception.StandingException;
import com.sapient.football.manager.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(value = ServiceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping(produces = "application/json")
  private ApiResponse<Void> serviceException(InvalidDataException exception) {
    log.error("Handling ServiceException", exception);
    return ApiResponse.createErrorResponse(ApiError.fromErrorCode(exception.getError()));
  }

  @ExceptionHandler(value = StandingException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @RequestMapping(produces = "application/json")
  private ApiResponse<Void> standingException(StandingException exception) {
    log.error("Handling StandingException", exception);
    return ApiResponse.createErrorResponse(ApiError.fromErrorCode(exception.getError()));
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @RequestMapping(produces = "application/json")
  private ApiResponse<Void> unknownException(Exception exception) {
    log.error("Handling UnknownException", exception);
    return ApiResponse.createErrorResponse(ApiError.fromErrorCode(ErrorCode.E1));
  }
}
