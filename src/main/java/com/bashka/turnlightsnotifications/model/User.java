package com.bashka.turnlightsnotifications.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user")
public class User {
    @Id
    String chatId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(chatId, user.chatId);
    }

    @Override
    public int hashCode() {
        return chatId != null ? chatId.hashCode() : 0;
    }
}
