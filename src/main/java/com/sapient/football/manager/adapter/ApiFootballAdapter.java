package com.sapient.football.manager.adapter;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.domain.country.Country;
import com.sapient.football.manager.domain.league.League;
import com.sapient.football.manager.domain.standing.Standing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ApiFootballAdapter implements ApiFootballPort {
  private final ApiFootballClient apiFootballClient;
  @Value("${apiFootballApiKey}")
  private String apiKey;
  private final String ACTION_GET_LEAGUES = "get_leagues";
  private final String ACTION_GET_COUNTRIES = "get_countries";
  private final String ACTION_GET_STANDINGS = "get_standings";

  public ApiFootballAdapter(ApiFootballClient apiFootballClient) {
    this.apiFootballClient = apiFootballClient;
  }


  @Override
  public List<Standing> getStandings(Long leagueId, Long countryId) {
    return apiFootballClient.getStandings(ACTION_GET_STANDINGS, apiKey, leagueId)
        .stream()
        .map(response -> response.toStanding(countryId))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Country> getCountry(String countryName) {
    var country = apiFootballClient.getCountry(ACTION_GET_COUNTRIES, apiKey);
    return country.stream().filter(c -> c.getName().equals(countryName)).findFirst().map(CountryResponse::toCountry);
  }

  @Override
  public Optional<League> getLeague(String leagueName, Long countryId) {
    var league = apiFootballClient.getLeague(ACTION_GET_LEAGUES, apiKey, countryId);
    return league.stream().filter(l -> l.getName().equals(leagueName)).findFirst().map(LeagueResponse::toLeague);

  }
}
