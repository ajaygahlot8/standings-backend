package com.sapient.football.manager.domain.league;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class League {
  @NonNull
  Long id;
  @NonNull
  String name;
}
