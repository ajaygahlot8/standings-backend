package com.sapient.football.manager.rest.controller;

import com.sapient.football.manager.domain.standing.Standing;
import com.sapient.football.manager.domain.standing.StandingService;
import com.sapient.football.manager.rest.ApiResponse;
import com.sapient.football.manager.rest.response.GetStandingsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StandingController {

  private final StandingService standingService;

  public StandingController(StandingService standingService) {
    this.standingService = standingService;
  }

  @GetMapping(value = "/standings")
  public ApiResponse<GetStandingsResponse> getStandings(
      @RequestParam String countryName,
      @RequestParam String leagueName,
      @RequestParam String teamName
  ) {
    log.info("Received request to fetch standings for countryName {} , leagueName {} and teamName {}", countryName, leagueName, teamName);
    var standing = standingService.getStanding(countryName, leagueName, teamName);
    return ApiResponse.createSuccessResponse(GetStandingsResponse.from(standing));
  }

}
