package com.bashka.turnlightsnotifications.telegram.enums;

import java.util.HashMap;
import java.util.Map;

public enum ButtonNameEnum {
    CONFIGURE("Підписатись на сповіщення"),
    MAIN_MENU("Головне меню"),
    FIRST_GROUP("Перша група"),
    SECOND_GROUP("Друга група"),
    THIRD_GROUP("Третя група"),
    HELP_BUTTON("Як користуватися ботом?"), SETTINGS("Налаштування"), FIRST_GROUP_REMOVE("Видалити першу групу"), SECOND_GROUP_REMOVE("Видалити другу групу"), THIRD_GROUP_REMOVE("Видалити третю групу"), REMOVE_ALL("Видалити всі групи");

    private static final Map<String, ButtonNameEnum> buttonNames = new HashMap<>();

    static {
        for (ButtonNameEnum buttonNameEnum : ButtonNameEnum.values()) {
            buttonNames.put(buttonNameEnum.getButtonName(), buttonNameEnum);
        }
    }

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public static ButtonNameEnum getButtonNameEnum(String buttonName) {
        return buttonNames.get(buttonName);
    }
}