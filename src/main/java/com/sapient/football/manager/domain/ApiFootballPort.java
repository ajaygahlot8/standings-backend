package com.sapient.football.manager.domain;

import com.sapient.football.manager.domain.country.Country;
import com.sapient.football.manager.domain.league.League;
import com.sapient.football.manager.domain.standing.Standing;

import java.util.List;
import java.util.Optional;

public interface ApiFootballPort {
  List<Standing> getStandings(Long id, Long countryId);

  Optional<Country> getCountry(String countryName);

  Optional<League> getLeague(String leagueName, Long countryId);
}
