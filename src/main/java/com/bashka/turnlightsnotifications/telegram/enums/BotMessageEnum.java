package com.bashka.turnlightsnotifications.telegram.enums;

public enum BotMessageEnum {
    EXCEPTION_ILLEGAL_MESSAGE("Я б з радістю с тобою поспілкувався, але я вмію лише через кнопки"),
    EXCEPTION_WHAT_THE_FUCK("Щось пішло не так. Я в шоці. Напишіть мені, будь ласка, @BALLIKAxJAVA"),
    HELP_MESSAGE("""
            Як користуватися ботом?
            Максимально просто, підписуєтесь на сповіщення, вибираєте групу, яка вас цікавить, і отримуєте сповіщення про вимкнення світла.
            Ці данні на основі інформації від ДТЕК, також є офіційний канал в вайбері з реільними анонсами вимкнення світла. ДТЕК info.
            Сповіщення будуть приходить приблизно за годину до відключення світла.
            Згодом функціонал буде розширюватись, слідкуйте за новинами.
            
            Автор @BALLIKAxJAVA, якщо є зауваження які я не помітив, пишіть.
            Майте на увазі, бот трішки сиренький і можуть бути казуси.
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