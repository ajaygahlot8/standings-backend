package com.sapient.football.manager.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.football.manager.domain.country.Country;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class CountryResponse {
  @JsonProperty("country_id")
  Long id;
  @JsonProperty("country_name")
  String name;

 public Country toCountry(){
    return Country.builder()
        .id(this.id)
        .name(this.name)
        .build();
  }
}
