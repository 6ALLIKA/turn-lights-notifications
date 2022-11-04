package com.bashka.turnlightsnotifications.initializer;

import com.bashka.turnlightsnotifications.dao.TurnLightScheduleRepository;
import com.bashka.turnlightsnotifications.model.TurnLightDay;
import com.bashka.turnlightsnotifications.model.TurnLightSchedule;
import com.bashka.turnlightsnotifications.model.TurnLightTimeRange;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InitializingBeanImpl implements InitializingBean {
    TurnLightScheduleRepository turnLightScheduleRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        TurnLightTimeRange firstSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(0, 0))
                .to(LocalTime.of(1, 0))
                .build();
        TurnLightTimeRange secondSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(0, 0))
                .to(LocalTime.of(4, 0))
                .build();
        TurnLightTimeRange thirdSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(3, 0))
                .to(LocalTime.of(7, 0))
                .build();
        TurnLightTimeRange fourthSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(6, 0))
                .to(LocalTime.of(10, 0))
                .build();
        TurnLightTimeRange fifthSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(9, 0))
                .to(LocalTime.of(13, 0))
                .build();
        TurnLightTimeRange sixthSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(12, 0))
                .to(LocalTime.of(16, 0))
                .build();
        TurnLightTimeRange seventhSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(15, 0))
                .to(LocalTime.of(19, 0))
                .build();
        TurnLightTimeRange eighthSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(18, 0))
                .to(LocalTime.of(22, 0))
                .build();
        TurnLightTimeRange ninthSlot = TurnLightTimeRange.builder()
                .from(LocalTime.of(21, 0))
                .to(LocalTime.of(23, 59))
                .build();
        TurnLightDay secondGroupMonday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(firstSlot, thirdSlot, sixthSlot, ninthSlot))
                .on(List.of(secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay secondGroupTuesday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupWednesday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
                .on(List.of(fifthSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupThursday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(thirdSlot, sixthSlot, ninthSlot))
                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay secondGroupFriday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupSaturday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupSunday = TurnLightDay.builder()
                .groupNumber("Second")
                .off(List.of(thirdSlot,sixthSlot, ninthSlot))
                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightSchedule secondGroupSchedule = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupMonday))
                .dayOfWeek("MONDAY")
                .build();
        TurnLightSchedule secondGroupSchedule1 = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupTuesday))
                .dayOfWeek("TUESDAY")
                .build();
        TurnLightSchedule secondGroupSchedule2 = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupWednesday))
                .dayOfWeek("WEDNESDAY")
                .build();
        TurnLightSchedule secondGroupSchedule3 = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupThursday))
                .dayOfWeek("THURSDAY")
                .build();
        TurnLightSchedule secondGroupSchedule4 = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupFriday))
                .dayOfWeek("FRIDAY")
                .build();
        TurnLightSchedule secondGroupSchedule5 = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupSaturday))
                .dayOfWeek("SATURDAY")
                .build();
        TurnLightSchedule secondGroupSchedule6 = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupSunday))
                .dayOfWeek("SUNDAY")
                .build();
        List<TurnLightSchedule> secondGroupData = List.of(secondGroupSchedule, secondGroupSchedule1, secondGroupSchedule2, secondGroupSchedule3, secondGroupSchedule4, secondGroupSchedule5, secondGroupSchedule6);
        turnLightScheduleRepository.saveAll(secondGroupData);
    }
}