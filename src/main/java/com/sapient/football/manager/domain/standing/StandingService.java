package com.sapient.football.manager.domain.standing;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.domain.country.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingService {

  ApiFootballPort apiFootballPort;

  CountryService countryService;


  public List<Standing> getStandings(String countryName,
                                     String leagueName,
                                     String teamName) {
    //TODO
    return null;
  }
}
