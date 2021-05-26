package com.sapient.football.manager.domain.standing;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.domain.ServiceException;
import com.sapient.football.manager.domain.StandingException;
import com.sapient.football.manager.domain.country.CountryService;
import com.sapient.football.manager.domain.league.LeagueService;
import com.sapient.football.manager.enums.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StandingService {

  ApiFootballPort apiFootballPort;

  CountryService countryService;

  LeagueService leagueService;


  public Standing getStanding(String countryName,
                              String leagueName,
                              String teamName) {
    validateRequestData(countryName, leagueName, teamName);
    var country = countryService.getCountry(countryName);

    var league = leagueService.getLeague(leagueName, country.getId());

    var standings = apiFootballPort.getStandings(league.getId());

    return standings.stream()
        .filter(standingData -> standingData.getTeamName().equals(teamName))
        .findFirst()
        .orElseThrow(() -> new StandingException(ErrorCode.S4));
  }

  private void validateRequestData(String countryName, String leagueName, String teamName) {
    if (!StringUtils.hasLength(countryName)) {
      throw new ServiceException(ErrorCode.S1);
    }

    if (!StringUtils.hasLength(leagueName)) {
      throw new ServiceException(ErrorCode.S2);
    }
    if (!StringUtils.hasLength(teamName)) {
      throw new ServiceException(ErrorCode.S3);
    }
  }
}
