package com.sapient.football.manager.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

  E1("Internal Server Error"),,
  S1("country name should not be empty"),
  S2("league name should not be empty"),
  S3("team name should not be empty"),
  S4("Standing data does not exist for this team");


  private final String message;
  private final String code;

  ErrorCode(String message) {
    this.code = this.name();
    this.message = message;
  }

}
