package com.bashka.turnlightsnotifications.service;

import com.bashka.turnlightsnotifications.dao.TurnLightScheduleRepository;
import com.bashka.turnlightsnotifications.dao.UserRepository;
import com.bashka.turnlightsnotifications.model.Group;
import com.bashka.turnlightsnotifications.model.TurnLightDay;
import com.bashka.turnlightsnotifications.model.TurnLightSchedule;
import com.bashka.turnlightsnotifications.model.TurnLightTimeRange;
import com.bashka.turnlightsnotifications.telegram.TelegramApiClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Scheduler {
    UserRepository userRepository;
    TurnLightScheduleRepository turnLightScheduleRepository;
    TelegramApiClient telegramApiClient;

    @Scheduled(cron = "0 0/20 * * * *")
    void schedule() {
        LocalDateTime now = LocalDateTime.now().plusHours(2);
        LocalTime nowLocalTime = now.toLocalTime();
        String todayDayOfWeek = now.getDayOfWeek().toString();
        Map<String, TurnLightSchedule> lightTurnsByGroup = StreamSupport.stream(turnLightScheduleRepository.findAll().spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(TurnLightSchedule::getGroupNumber, Function.identity()));
        userRepository.findAll().forEach(user -> {
            user.getGroupNumbers().forEach(groupNumber -> {
                TurnLightSchedule turnLightSchedule = lightTurnsByGroup.get(groupNumber.getGroupNumber());
                List<TurnLightDay> todayTurnsOffOns = new ArrayList<>();
                for (TurnLightDay turnLightDay : turnLightSchedule.getScheduleInformation()) {
                    if (turnLightDay.getDayOfWeek().equals(todayDayOfWeek)) {
                        todayTurnsOffOns.add(turnLightDay);
                    }
                }
                if (todayTurnsOffOns.isEmpty()) {
                    return;
                }
                for (TurnLightDay todayTurnsOffOn : todayTurnsOffOns) {
                    Group userGroup = user.getGroupNumbers().stream()
                            .filter(group -> group.getGroupNumber().equals(groupNumber.getGroupNumber()))
                            .findFirst()
                            .orElseThrow();
                    Optional<TurnLightTimeRange> turnOff = todayTurnsOffOn.getOff().stream()
                            .filter(time -> isTurnOffIsNear(nowLocalTime, time))
                            .findFirst();
                    if (turnOff.isPresent()) {
                        if (!userGroup.isTurnedOffNotification() && Duration.between(nowLocalTime, turnOff.get().getFrom()).toMinutes() < 60) {
                            telegramApiClient.sendMessage(user.getChatId(), "Скоро вимкнуть світло в групі "
                                    + getTranslate(userGroup.getGroupNumber()) + " в " + turnOff.get().getFrom().toString()
                                    + " до " + turnOff.get().getTo().toString());
                            userGroup.setTurnedOffNotification(true);
                            userGroup.setTurnedOnNotification(false);
                            userRepository.save(user);
                        }
                    }
                    Optional<TurnLightTimeRange> turnOn = todayTurnsOffOn.getOff().stream()
                            .filter(time -> time.getFrom().isBefore(nowLocalTime) && time.getTo().isAfter(nowLocalTime))
                            .findFirst();
                    if (turnOn.isPresent()) {
                        if (!userGroup.isTurnedOnNotification() && Duration.between(nowLocalTime, turnOn.get().getTo()).toMinutes() <= 60) {
                            telegramApiClient.sendMessage(user.getChatId(), "Скоро ввімкнуть світло в групі "
                                    + getTranslate(userGroup.getGroupNumber()) + " через " + Duration.between(nowLocalTime, turnOn.get().getTo()).toMinutes()
                                    + " хвилин");
                            userGroup.setTurnedOnNotification(true);
                            userGroup.setTurnedOffNotification(false);
                            userRepository.save(user);
                        }
                    }
                }
            });
        });
    }

    private boolean isTurnOffIsNear(LocalTime nowLocalTime, TurnLightTimeRange turnOff) {
        return Duration.between(nowLocalTime, turnOff.getFrom()).toMinutes() < 60 && Duration.between(nowLocalTime, turnOff.getFrom()).toMinutes() > 0;
    }

    private String getTranslate(String groupNumber) {
        return switch (groupNumber) {
            case "First" -> "1";
            case "Second" -> "2";
            case "Third" -> "3";
            default -> throw new IllegalStateException("Unexpected value: " + groupNumber);
        };
    }
}
