package com.sapient.football.manager.adapter;

import com.sapient.football.manager.domain.country.Country;
import com.sapient.football.manager.domain.league.League;
import com.sapient.football.manager.domain.standing.Standing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "api-football", url = "https://apiv3.apifootball.com/")
public interface ApiFootballClient {

  @GetMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE)
  List<CountryResponse> getCountry(
      @RequestParam(value = "action") String action,
      @RequestParam(value = "APIkey") String apiKey);

  @GetMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE)
  List<LeagueResponse> getLeague(
      @RequestParam(value = "action") String action,
      @RequestParam(value = "APIkey") String apiKey,
      @RequestParam(value = "country_id") Long countryId);

  @GetMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE)
  List<StandingResponse> getStandings(
      @RequestParam(value = "action") String action,
      @RequestParam(value = "APIkey") String apiKey,
      @RequestParam(value = "league_id") Long leagueId);
}
