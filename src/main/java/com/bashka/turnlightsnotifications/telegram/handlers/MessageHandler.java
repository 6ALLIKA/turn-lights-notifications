package com.bashka.turnlightsnotifications.telegram.handlers;

import com.bashka.turnlightsnotifications.dao.UserRepository;
import com.bashka.turnlightsnotifications.model.User;
import com.bashka.turnlightsnotifications.telegram.enums.BotMessageEnum;
import com.bashka.turnlightsnotifications.telegram.enums.ButtonNameEnum;
import com.bashka.turnlightsnotifications.telegram.keyboards.ReplyKeyboardMaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageHandler {
    ReplyKeyboardMaker replyKeyboardMaker;
    UserRepository userRepository;

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();
        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId);
        }else if (inputText.equals(ButtonNameEnum.CONFIGURE.getButtonName())) {
            return getConfigureMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.HELP_BUTTON.getButtonName())) {
            SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
            sendMessage.enableMarkdown(true);
            return sendMessage;
        } else {
            return new SendMessage(chatId, BotMessageEnum.NON_COMMAND_MESSAGE.getMessage());
        }
    }

    private BotApiMethod<?> getConfigureMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getConfigureKeyboard());
        return sendMessage;
    }

    private BotApiMethod<?> saveUser(String chatId) {
        User user = new User();
        user.setChatId(chatId);
        userRepository.save(user);
        return getStartMessage(chatId);
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

}