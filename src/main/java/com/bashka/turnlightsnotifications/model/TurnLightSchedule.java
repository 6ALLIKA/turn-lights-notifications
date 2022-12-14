package com.bashka.turnlightsnotifications.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("turn_light_schedule")
@ToString
public class TurnLightSchedule {

    @Id
    String groupNumber;
    List<TurnLightDay> scheduleInformation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurnLightSchedule that = (TurnLightSchedule) o;

        if (!Objects.equals(groupNumber, that.groupNumber)) return false;
        return Objects.equals(scheduleInformation, that.scheduleInformation);
    }

    @Override
    public int hashCode() {
        int result = groupNumber != null ? groupNumber.hashCode() : 0;
        result = 31 * result + (scheduleInformation != null ? scheduleInformation.hashCode() : 0);
        return result;
    }
}
