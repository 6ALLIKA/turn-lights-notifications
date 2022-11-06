package com.bashka.turnlightsnotifications.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisHash;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@RedisHash("user")
@ToString
public class Group {
    public static final Group FIRST = new Group("First");
    public static final Group SECOND = new Group("Second");
    public static final Group THIRD = new Group("Third");
    String groupNumber;
    boolean isTurnedOnNotification;
    boolean isTurnedOffNotification;

    public Group(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return groupNumber.equals(group.groupNumber);
    }

    @Override
    public int hashCode() {
        return groupNumber.hashCode();
    }
}
