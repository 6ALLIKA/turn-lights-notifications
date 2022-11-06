package com.bashka.turnlightsnotifications.telegram.handlers;

import com.bashka.turnlightsnotifications.dao.UserRepository;
import com.bashka.turnlightsnotifications.model.Group;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            return getDefaultMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonNameEnum.MAIN_MENU.getButtonName())) {
            return getDefaultMessage(chatId, "Оберіть дію");
        } else if (inputText.equals(ButtonNameEnum.CONFIGURE.getButtonName())) {
            return getConfigureMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.SETTINGS.getButtonName())) {
            return getSettingsMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.FIRST_GROUP.getButtonName()) ||
                inputText.equals(ButtonNameEnum.SECOND_GROUP.getButtonName()) ||
                inputText.equals(ButtonNameEnum.THIRD_GROUP.getButtonName())) {
            return saveUser(chatId, inputText);
        } else if (inputText.equals(ButtonNameEnum.FIRST_GROUP_REMOVE.getButtonName()) ||
                inputText.equals(ButtonNameEnum.SECOND_GROUP_REMOVE.getButtonName()) ||
                inputText.equals(ButtonNameEnum.THIRD_GROUP_REMOVE.getButtonName()) ||
                inputText.equals(ButtonNameEnum.REMOVE_ALL.getButtonName())) {
            return removeAction(chatId, inputText);
        } else if (inputText.equals(ButtonNameEnum.HELP_BUTTON.getButtonName())) {
            SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
            sendMessage.enableMarkdown(true);
            return sendMessage;
        } else {
            return new SendMessage(chatId, BotMessageEnum.NON_COMMAND_MESSAGE.getMessage());
        }
    }

    private BotApiMethod<?> getSettingsMessage(String chatId) {
        Optional<User> byId = userRepository.findById(chatId);
        if (byId.isPresent()) {
            User user = byId.get();
            return getRemoveMessage(user, "Можете обрати групу, яку бажаєте видалити");
        } else {
            return getDefaultMessage(chatId, "Ви ще не налаштували групи");
        }
    }

    private BotApiMethod<?> removeAction(String chatId, String inputText) {
        Optional<User> byId = userRepository.findById(chatId);
        if (byId.isPresent()) {
            User user = byId.get();
            Set<Group> groups = user.getGroupNumbers();
            if (inputText.equals(ButtonNameEnum.FIRST_GROUP_REMOVE.getButtonName())) {
                groups.remove(Group.FIRST);
            } else if (inputText.equals(ButtonNameEnum.SECOND_GROUP_REMOVE.getButtonName())) {
                groups.remove(Group.SECOND);
            } else if (inputText.equals(ButtonNameEnum.THIRD_GROUP_REMOVE.getButtonName())) {
                groups.remove(Group.THIRD);
            } else if (inputText.equals(ButtonNameEnum.REMOVE_ALL.getButtonName())) {
                groups.clear();
            }
            user.setGroupNumbers(groups);
            userRepository.save(user);
            return getRemoveMessage(user, "Налаштування збережено");
        } else {
            return getDefaultMessage(chatId, "Ви не зареєстровані");
        }
    }

    private BotApiMethod<?> getRemoveMessage(User user, String message) {
        SendMessage sendMessage = new SendMessage(user.getChatId(), message);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getRemoveKeyboard(user));
        return sendMessage;
    }

    private BotApiMethod<?> getConfigureMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, """
                Оберіть групу, можете обрати декілька.
                Дізнатися свою групу можна за посиланням: https://www.dtek-kem.com.ua/ua/shutdowns
                """);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getConfigureKeyboard());
        return sendMessage;
    }

    private BotApiMethod<?> saveUser(String chatId, String inputText) {
        User user = new User();
        Optional<User> byId = userRepository.findById(chatId);
        if (byId.isPresent()) {
            user = byId.get();
        } else {
            user.setChatId(chatId);
            user.setGroupNumbers(new HashSet<>());
        }
        ButtonNameEnum buttonNameEnum = ButtonNameEnum.getButtonNameEnum(inputText);
        switch (buttonNameEnum) {
            case FIRST_GROUP -> user.getGroupNumbers().add(Group.builder().groupNumber("First").isTurnedOnNotification(true).build());
            case SECOND_GROUP -> user.getGroupNumbers().add(Group.builder().groupNumber("Second").isTurnedOnNotification(true).build());
            case THIRD_GROUP -> user.getGroupNumbers().add(Group.builder().groupNumber("Third").isTurnedOnNotification(true).build());
            default -> throw new IllegalArgumentException();
        }
        userRepository.save(user);
        return getDefaultMessage(chatId, "Налаштування збережено");
    }

    private SendMessage getDefaultMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

}