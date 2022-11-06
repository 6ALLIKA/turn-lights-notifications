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
        turnLightScheduleRepository.deleteAll();
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
        TurnLightDay firstGroupMonday = TurnLightDay.builder()
                .dayOfWeek("MONDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay firstGroupTuesday = TurnLightDay.builder()
                .dayOfWeek("TUESDAY")
                .off(List.of(thirdSlot, sixthSlot, ninthSlot))
//                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay firstGroupWednesday = TurnLightDay.builder()
                .dayOfWeek("WEDNESDAY")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
//                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay firstGroupThursday = TurnLightDay.builder()
                .dayOfWeek("THURSDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay firstGroupFriday = TurnLightDay.builder()
                .dayOfWeek("FRIDAY")
                .off(List.of(thirdSlot, sixthSlot, ninthSlot))
//                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay firstGroupSaturday = TurnLightDay.builder()
                .dayOfWeek("SATURDAY")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
//                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay firstGroupSunday = TurnLightDay.builder()
                .dayOfWeek("SUNDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightSchedule firstGroupSchedule = TurnLightSchedule.builder()
                .scheduleInformation(List.of(firstGroupMonday, firstGroupTuesday, firstGroupWednesday, firstGroupThursday, firstGroupFriday, firstGroupSaturday, firstGroupSunday))
                .groupNumber("First")
                .build();
        List<TurnLightSchedule> firstGroupData = List.of(firstGroupSchedule);
        TurnLightDay secondGroupMonday = TurnLightDay.builder()
                .dayOfWeek("MONDAY")
                .off(List.of(firstSlot, thirdSlot, sixthSlot, ninthSlot))
//                .on(List.of(secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay secondGroupTuesday = TurnLightDay.builder()
                .dayOfWeek("TUESDAY")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
//                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupWednesday = TurnLightDay.builder()
                .dayOfWeek("WEDNESDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(fifthSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupThursday = TurnLightDay.builder()
                .dayOfWeek("THURSDAY")
                .off(List.of(thirdSlot, sixthSlot, ninthSlot))
//                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay secondGroupFriday = TurnLightDay.builder()
                .dayOfWeek("FRIDAY")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
//                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupSaturday = TurnLightDay.builder()
                .dayOfWeek("SATURDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay secondGroupSunday = TurnLightDay.builder()
                .dayOfWeek("SUNDAY")
                .off(List.of(thirdSlot,sixthSlot, ninthSlot))
//                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightSchedule secondGroupSchedule = TurnLightSchedule.builder()
                .scheduleInformation(List.of(secondGroupMonday, secondGroupTuesday, secondGroupWednesday, secondGroupThursday, secondGroupFriday, secondGroupSaturday, secondGroupSunday))
                .groupNumber("Second")
                .build();
        List<TurnLightSchedule> secondGroupData = List.of(secondGroupSchedule);
        TurnLightDay thirdGroupMonday = TurnLightDay.builder()
                .dayOfWeek("MONDAY")
                .off(List.of(fourthSlot, seventhSlot))
//                .on(List.of(firstSlot, secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay thirdGroupTuesday = TurnLightDay.builder()
                .dayOfWeek("TUESDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay thirdGroupWednesday = TurnLightDay.builder()
                .dayOfWeek("WEDNESDAY")
                .off(List.of(thirdSlot, sixthSlot, ninthSlot))
//                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay thirdGroupThursday = TurnLightDay.builder()
                .dayOfWeek("THURSDAY")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
//                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightDay thirdGroupFriday = TurnLightDay.builder()
                .dayOfWeek("FRIDAY")
                .off(List.of(secondSlot, fifthSlot, eighthSlot))
//                .on(List.of(firstSlot, thirdSlot, fourthSlot, sixthSlot, seventhSlot, ninthSlot))
                .build();
        TurnLightDay thirdGroupSaturday = TurnLightDay.builder()
                .dayOfWeek("SATURDAY")
                .off(List.of(thirdSlot, sixthSlot, ninthSlot))
//                .on(List.of(firstSlot, secondSlot, fourthSlot, fifthSlot, seventhSlot, eighthSlot))
                .build();
        TurnLightDay thirdGroupSunday = TurnLightDay.builder()
                .dayOfWeek("SUNDAY")
                .off(List.of(firstSlot, fourthSlot, seventhSlot))
//                .on(List.of(secondSlot, thirdSlot, fifthSlot, sixthSlot, eighthSlot, ninthSlot))
                .build();
        TurnLightSchedule thirdGroupSchedule = TurnLightSchedule.builder()
                .scheduleInformation(List.of(thirdGroupMonday, thirdGroupTuesday, thirdGroupWednesday, thirdGroupThursday, thirdGroupFriday, thirdGroupSaturday, thirdGroupSunday))
                .groupNumber("Third")
                .build();
        List<TurnLightSchedule> thirdGroupData = List.of(thirdGroupSchedule);
        turnLightScheduleRepository.saveAll(firstGroupData);
        turnLightScheduleRepository.saveAll(secondGroupData);
        turnLightScheduleRepository.saveAll(thirdGroupData);

    }
}