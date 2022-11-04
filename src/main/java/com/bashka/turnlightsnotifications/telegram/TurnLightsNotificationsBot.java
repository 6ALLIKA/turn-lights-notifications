package com.bashka.turnlightsnotifications.telegram;

import com.bashka.turnlightsnotifications.telegram.enums.BotMessageEnum;
import com.bashka.turnlightsnotifications.telegram.handlers.CallbackQueryHandler;
import com.bashka.turnlightsnotifications.telegram.handlers.MessageHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.IOException;

@Getter
@Setter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TurnLightsNotificationsBot extends SpringWebhookBot {
    @Value("${telegram.webhook-path}")
    String botPath;
    @Value("${telegram.bot-name}")
    String botUsername;
    @Value("${telegram.bot-token}")
    String botToken;

    final MessageHandler messageHandler;
    final CallbackQueryHandler callbackQueryHandler;

    public TurnLightsNotificationsBot(SetWebhook setWebhook, MessageHandler messageHandler,CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ILLEGAL_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
        }
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message != null) {
                return messageHandler.answerMessage(update.getMessage());
            }
        }
        return null;
    }
}
