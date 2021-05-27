package com.sapient.football.manager.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.football.manager.domain.country.Country;
import com.sapient.football.manager.domain.league.League;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class LeagueResponse {
  @JsonProperty("league_id")
  Long id;
  @JsonProperty("league_name")
  String name;

 public League toLeague(){
    return League.builder()
        .id(this.id)
        .name(this.name)
        .build();
  }
}
