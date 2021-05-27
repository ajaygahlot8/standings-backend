package com.sapient.football.manager.domain.league;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.enums.ErrorCode;
import com.sapient.football.manager.exception.StandingException;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {

  private final ApiFootballPort apiFootballPort;

  public LeagueService(ApiFootballPort apiFootballPort) {
    this.apiFootballPort = apiFootballPort;
  }

  public League getLeague(String leagueName, Long countryId) {
    return apiFootballPort.getLeague(leagueName, countryId)
        .orElseThrow(() -> new StandingException(ErrorCode.L1));  }
}
