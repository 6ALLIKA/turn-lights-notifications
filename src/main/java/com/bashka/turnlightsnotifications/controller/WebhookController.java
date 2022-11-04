package com.bashka.turnlightsnotifications.controller;

import com.bashka.turnlightsnotifications.dao.TurnLightScheduleRepository;
import com.bashka.turnlightsnotifications.dao.UserRepository;
import com.bashka.turnlightsnotifications.telegram.TurnLightsNotificationsBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class WebhookController {
    private final TurnLightsNotificationsBot turnLightsNotificationsBot;
    private final UserRepository userRepository;
    private final TurnLightScheduleRepository turnLightScheduleRepository;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return turnLightsNotificationsBot.onWebhookUpdateReceived(update);
    }

    @GetMapping("/")
    public boolean sendMessageToAllUsers(
            @RequestParam String message) {
        return turnLightsNotificationsBot.sendMessageToAllUsers(message);
    }

    @GetMapping("/users")
    public String getAllUsers() {
        userRepository.findAll().forEach(System.out::println);
        return "nice try";
    }
}