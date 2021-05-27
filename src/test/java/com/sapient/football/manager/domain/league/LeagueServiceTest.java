package com.sapient.football.manager.domain.league;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.domain.country.Country;
import com.sapient.football.manager.domain.country.CountryService;
import com.sapient.football.manager.enums.ErrorCode;
import com.sapient.football.manager.exception.StandingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LeagueServiceTest {

  private LeagueService leagueService;

  @Mock
  private ApiFootballPort apiFootballPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    leagueService = new LeagueService(apiFootballPort);
  }

  @Test
  @DisplayName("Should fetch league details ")
  void testGetLeague() {
    var leagueName = "leagueName";
    var countryId = 1L;

    var league = League.builder()
        .id(1L)
        .name(leagueName)
        .build();


    when(apiFootballPort.getLeague(leagueName, countryId)).thenReturn(Optional.of(league));

    var actual = leagueService.getLeague(leagueName, countryId);

    verify(apiFootballPort, times(1)).getLeague(leagueName, countryId);
    assertEquals(league, actual);
  }

  @Test
  @DisplayName("Should throw exception while fetch league if not found ")
  void shouldThrowErrorIfLeagueNotFound() {
    var leagueName = "leagueName";
    var countryId = 1L;
    when(apiFootballPort.getLeague(leagueName, countryId)).thenReturn(Optional.empty());

    var actual = assertThrows(StandingException.class,
        () -> leagueService.getLeague(leagueName, countryId));
    assertEquals(ErrorCode.L1, actual.getError());
  }

}
