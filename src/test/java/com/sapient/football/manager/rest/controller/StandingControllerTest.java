package com.sapient.football.manager.rest.controller;

import com.sapient.football.manager.domain.standing.Standing;
import com.sapient.football.manager.domain.standing.StandingService;
import com.sapient.football.manager.enums.ErrorCode;
import com.sapient.football.manager.exception.StandingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StandingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StandingService standingService;

  @Test
  public void testGetStanding() throws Exception {
    var leagueId = 1L;
    var leagueName = "leagueName";
    var countryId = 1L;
    var countryName = "countryName";
    var teamId = 1L;
    var teamName = "teamName";
    var overallLeaguePosition = 1L;

    var standing = Standing.builder()
        .countryName(countryName)
        .countryId(countryId)
        .teamId(teamId)
        .teamName(teamName)
        .leagueName(leagueName)
        .leagueId(leagueId)
        .overallLeaguePosition(overallLeaguePosition)
        .build();

    when(standingService.getStanding(countryName, leagueName, teamName)).thenReturn(standing);

    var expectedResponse = "{\"success\":true,\"data\":{\"countryId\":1,\"countryName\":\"countryName\",\"leagueId\":1,\"leagueName\":\"leagueName\",\"teamId\":1,\"teamName\":\"teamName\",\"overallLeaguePosition\":1}}";

    mockMvc.perform(get("/standing")
        .param("countryName", countryName)
        .param("leagueName", leagueName)
        .param("teamName", teamName)
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));

    verify(standingService, times(1)).getStanding(countryName, leagueName, teamName);

  }

  @Test
  @DisplayName("Should not be able to fetch standing due to some exception")
  void shouldReturnErrorResponseIfExceptionOccurs() throws Exception {
    var leagueName = "leagueName";
    var countryName = "countryName";
    var teamName = "teamName";

    doThrow(new StandingException(ErrorCode.S1)).when(standingService).getStanding(countryName, leagueName, teamName);

    var expectedResponse = "{\"success\":false,\"error\":{\"code\":\"S1\",\"message\":\"country name should not be empty\"}}";

    mockMvc.perform(get("/standing")
        .param("countryName", countryName)
        .param("leagueName", leagueName)
        .param("teamName", teamName)
    )
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(content().json(expectedResponse));

    verify(standingService, times(1)).getStanding(countryName, leagueName, teamName);

  }

}
