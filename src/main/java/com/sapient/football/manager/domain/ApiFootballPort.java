package com.sapient.football.manager.domain;

import com.sapient.football.manager.domain.standing.Standing;

import java.util.List;

public interface ApiFootballPort {
  List<Standing> getStandings(Long id);
}
