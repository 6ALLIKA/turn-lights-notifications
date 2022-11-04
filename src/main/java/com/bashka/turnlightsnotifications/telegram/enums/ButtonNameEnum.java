package com.bashka.turnlightsnotifications.telegram.enums;

public enum ButtonNameEnum {
    CONFIGURE("Налаштувати сповіщення"),
    FIRST_GROUP("Перша група"),
    SECOND_GROUP("Друга група"),
    THIRD_GROUP("Третя група"),
    HELP_BUTTON("Як користуватися ботом?");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}