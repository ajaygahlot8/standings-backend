package com.sapient.football.manager.domain.country;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.enums.ErrorCode;
import com.sapient.football.manager.exception.StandingException;
import org.springframework.stereotype.Service;


@Service
public class CountryService {

  private final ApiFootballPort apiFootballPort;

  public CountryService(ApiFootballPort apiFootballPort) {
    this.apiFootballPort = apiFootballPort;
  }


  public Country getCountry(String countryName) {

    return apiFootballPort.getCountry(countryName)
        .orElseThrow(() -> new StandingException(ErrorCode.C1));
  }
}
