package com.bashka.turnlightsnotifications.telegram.enums;

public enum BotMessageEnum {
    EXCEPTION_ILLEGAL_MESSAGE("Я б з радістю с тобою поспілкувався, але я вмію лише через кнопки"),
    EXCEPTION_WHAT_THE_FUCK("Щось пішло не так. Я в шоці. Напишіть мені, будь ласка, @BALLIKAxJAVA"),
    HELP_MESSAGE("""
            In development
            """),
    NON_COMMAND_MESSAGE("Я б з радістю с тобою поспілкувався, але я вмію лише через кнопки");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}