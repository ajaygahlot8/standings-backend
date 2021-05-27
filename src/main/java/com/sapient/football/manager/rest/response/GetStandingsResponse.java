package com.sapient.football.manager.rest.response;

import com.sapient.football.manager.domain.standing.Standing;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class GetStandingsResponse {

  @NonNull
  Long countryId;
  @NonNull
  String countryName;
  @NonNull
  Long leagueId;
  @NonNull
  String leagueName;
  @NonNull
  Long teamId;
  @NonNull
  String teamName;
  @NonNull
  Long overallLeaguePosition;

  public static GetStandingsResponse from(Standing standing) {
    return GetStandingsResponse.builder()
        .teamName(standing.getTeamName())
        .teamId(standing.getTeamId())
        .countryId(standing.getCountryId())
        .countryName(standing.getCountryName())
        .leagueId(standing.getLeagueId())
        .leagueName(standing.getLeagueName())
        .overallLeaguePosition(standing.getOverallLeaguePosition())
        .build();
  }
}
