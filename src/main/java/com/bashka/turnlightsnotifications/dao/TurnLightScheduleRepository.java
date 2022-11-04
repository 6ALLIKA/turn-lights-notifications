package com.bashka.turnlightsnotifications.dao;

import com.bashka.turnlightsnotifications.model.TurnLightSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnLightScheduleRepository extends CrudRepository<TurnLightSchedule, String> {
}