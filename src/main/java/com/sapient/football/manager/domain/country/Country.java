package com.sapient.football.manager.domain.country;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class Country {
  @NonNull
  Long id;
  @NonNull
  String name;
}
