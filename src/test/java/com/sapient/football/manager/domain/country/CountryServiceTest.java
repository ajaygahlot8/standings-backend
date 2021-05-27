package com.sapient.football.manager.domain.country;

import com.sapient.football.manager.adapter.CountryResponse;
import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.enums.ErrorCode;
import com.sapient.football.manager.exception.StandingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CountryServiceTest {

  private CountryService countryService;

  @Mock
  private ApiFootballPort apiFootballPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    countryService = new CountryService(apiFootballPort);
  }

  @Test
  @DisplayName("Should fetch country details ")
  void testGetCountry() {
    var countryName = "countryName";

    var country = Country.builder()
        .id(1L)
        .name(countryName)
        .build();


    when(apiFootballPort.getCountry(countryName)).thenReturn(Optional.of(country));

    var actual = countryService.getCountry(countryName);

    verify(apiFootballPort, times(1)).getCountry(countryName);
    assertEquals(country, actual);
  }

  @Test
  @DisplayName("Should throw exception while fetch country if not found ")
  void shouldThrowErrorIfCountryNotFound() {
    var countryName = "countryName";

    when(apiFootballPort.getCountry(countryName)).thenReturn(Optional.empty());

    var actual = assertThrows(StandingException.class,
        () -> countryService.getCountry(countryName));
    assertEquals(ErrorCode.C1, actual.getError());
  }

}
