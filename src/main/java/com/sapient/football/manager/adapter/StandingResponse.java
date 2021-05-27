package com.sapient.football.manager.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.football.manager.domain.league.League;
import com.sapient.football.manager.domain.standing.Standing;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class StandingResponse {
  @JsonProperty("country_name")
  String countryName;

  @JsonProperty("league_id")
  Long leagueId;

  @JsonProperty("league_name")
  String leagueName;

  @JsonProperty("team_id")
  Long teamId;

  @JsonProperty("team_name")
  String teamName;

  @JsonProperty("overall_league_position")
  Long overallLeaguePosition;

  public Standing toStanding(Long countryId) {
    return Standing.builder()
        .countryId(countryId)
        .teamId(this.teamId)
        .teamName(this.teamName)
        .countryName(this.countryName)
        .leagueName(this.leagueName)
        .leagueId(this.leagueId)
        .overallLeaguePosition(this.overallLeaguePosition)
        .build();
  }
}
