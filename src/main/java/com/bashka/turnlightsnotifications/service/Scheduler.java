package com.bashka.turnlightsnotifications.service;

import com.bashka.turnlightsnotifications.dao.UserRepository;
import com.bashka.turnlightsnotifications.telegram.TelegramApiClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Scheduler {
    UserRepository userRepository;
    TelegramApiClient telegramApiClient;

    @Scheduled(cron = "0 0/10 * * * * ?")
    void schedule() {
        userRepository.findAll().forEach(user -> {
            telegramApiClient.sendMessage(user.getChatId(), "Test scheduler");
        });
    }
}
