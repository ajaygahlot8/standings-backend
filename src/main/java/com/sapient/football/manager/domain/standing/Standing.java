package com.sapient.football.manager.domain.standing;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class Standing {
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
}
