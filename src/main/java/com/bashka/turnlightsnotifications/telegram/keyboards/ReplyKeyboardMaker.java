package com.bashka.turnlightsnotifications.telegram.keyboards;

import com.bashka.turnlightsnotifications.model.Group;
import com.bashka.turnlightsnotifications.model.User;
import com.bashka.turnlightsnotifications.telegram.enums.ButtonNameEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplyKeyboardMaker {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.CONFIGURE.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.SETTINGS.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnum.HELP_BUTTON.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getConfigureKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.FIRST_GROUP.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.SECOND_GROUP.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.THIRD_GROUP.getButtonName()));
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnum.MAIN_MENU.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnum.SETTINGS.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getRemoveKeyboard(User user) {
        KeyboardRow row1 = new KeyboardRow();
        for (Group groupNumber : user.getGroupNumbers()) {
            if (groupNumber.getGroupNumber().equals("First")) {
                row1.add(new KeyboardButton(ButtonNameEnum.FIRST_GROUP_REMOVE.getButtonName()));
            } else if (groupNumber.getGroupNumber().equals("Second")) {
                row1.add(new KeyboardButton(ButtonNameEnum.SECOND_GROUP_REMOVE.getButtonName()));
            } else if (groupNumber.getGroupNumber().equals("Thrid")) {
                row1.add(new KeyboardButton(ButtonNameEnum.THIRD_GROUP_REMOVE.getButtonName()));
            }
        }
        row1.add(new KeyboardButton(ButtonNameEnum.REMOVE_ALL.getButtonName()));
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnum.MAIN_MENU.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}