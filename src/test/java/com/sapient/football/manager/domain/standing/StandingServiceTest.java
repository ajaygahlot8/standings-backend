package com.sapient.football.manager.domain.standing;

import com.sapient.football.manager.domain.ApiFootballPort;
import com.sapient.football.manager.domain.country.Country;
import com.sapient.football.manager.domain.country.CountryService;
import com.sapient.football.manager.domain.league.League;
import com.sapient.football.manager.domain.league.LeagueService;
import com.sapient.football.manager.enums.ErrorCode;
import com.sapient.football.manager.exception.InvalidDataException;
import com.sapient.football.manager.exception.StandingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StandingServiceTest {

  @Mock
  private CountryService countryService;

  @Mock
  private LeagueService leagueService;

  @Mock
  private ApiFootballPort apiFootballPort;

  private StandingService standingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    standingService = new StandingService(apiFootballPort, countryService, leagueService);
  }

  @Test
  @DisplayName("Should fetch standing details ")
  void testGetStanding() {
    var leagueId = 1L;
    var leagueName = "leagueName";
    var countryId = 1L;
    var countryName = "countryName";
    var teamId = 1L;
    var teamName = "teamName";
    var overallLeaguePosition = 1L;

    var country = Country.builder()
        .id(countryId)
        .name(countryName)
        .build();

    var league = League.builder()
        .id(leagueId)
        .name(leagueName)
        .build();

    var standing = Standing.builder()
        .countryName(countryName)
        .countryId(countryId)
        .teamId(teamId)
        .teamName(teamName)
        .leagueName(leagueName)
        .leagueId(leagueId)
        .overallLeaguePosition(overallLeaguePosition)
        .build();


    when(countryService.getCountry(countryName)).thenReturn(country);
    when(leagueService.getLeague(leagueName, countryId)).thenReturn(league);
    when(apiFootballPort.getStandings(league.getId(), country.getId())).thenReturn(List.of(standing));

    var actual = standingService.getStanding(countryName, leagueName, teamName);

    verify(countryService, times(1)).getCountry(countryName);
    verify(leagueService, times(1)).getLeague(leagueName, countryId);
    verify(apiFootballPort, times(1)).getStandings(league.getId(), country.getId());

    assertEquals(standing, actual);
  }

  @Test
  @DisplayName("Should throw exception while fetch standing with null countryName ")
  void shouldThrowErrorIfCountryNameIsBlank() {

    var actual = assertThrows(InvalidDataException.class,
        () -> standingService.getStanding("", "leagueName", "teamName"));
    assertEquals(ErrorCode.S1, actual.getError());
  }

  @Test
  @DisplayName("Should throw exception while fetch standing with null leagueName ")
  void shouldThrowErrorIfLeagueNameIsBlank() {

    var actual = assertThrows(InvalidDataException.class,
        () -> standingService.getStanding("countryName", "", "teamName"));
    assertEquals(ErrorCode.S2, actual.getError());
  }

  @Test
  @DisplayName("Should throw exception while fetch standing with null teamName ")
  void shouldThrowErrorIfTeamNameIsBlank() {

    var actual = assertThrows(InvalidDataException.class,
        () -> standingService.getStanding("countryName", "leagueName", ""));
    assertEquals(ErrorCode.S3, actual.getError());
  }

  @Test
  @DisplayName("Should throw error if while fetch standing details teamName does not match ")
  void testGetStandingWithInvalidTeamName() {
    var leagueId = 1L;
    var leagueName = "leagueName";
    var countryId = 1L;
    var countryName = "countryName";
    var teamId = 1L;
    var teamName = "teamName";
    var overallLeaguePosition = 1L;

    var country = Country.builder()
        .id(countryId)
        .name(countryName)
        .build();

    var league = League.builder()
        .id(leagueId)
        .name(leagueName)
        .build();

    var standing = Standing.builder()
        .countryName(countryName)
        .countryId(countryId)
        .teamId(teamId)
        .teamName(teamName)
        .leagueName(leagueName)
        .leagueId(leagueId)
        .overallLeaguePosition(overallLeaguePosition)
        .build();


    when(countryService.getCountry(countryName)).thenReturn(country);
    when(leagueService.getLeague(leagueName, countryId)).thenReturn(league);
    when(apiFootballPort.getStandings(league.getId(), country.getId())).thenReturn(List.of(standing));

    var actual = assertThrows(StandingException.class,
        () -> standingService.getStanding(countryName, leagueName, "differentTeamName"));
    verify(countryService, times(1)).getCountry(countryName);
    verify(leagueService, times(1)).getLeague(leagueName, countryId);
    verify(apiFootballPort, times(1)).getStandings(league.getId(), country.getId());

    assertEquals(ErrorCode.S4, actual.getError());
  }
}
