package com.bashka.turnlightsnotifications.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TurnLightDay {
    String dayOfWeek;
    List<TurnLightTimeRange> off;
//    List<TurnLightTimeRange> on;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurnLightDay that = (TurnLightDay) o;

        if (!Objects.equals(dayOfWeek, that.dayOfWeek)) return false;
//        if (!Objects.equals(off, that.off)) return false;

        return Objects.equals(off, that.off);
//        return Objects.equals(on, that.on);
    }

    @Override
    public int hashCode() {
        int result = dayOfWeek != null ? dayOfWeek.hashCode() : 0;
        result = 31 * result + (off != null ? off.hashCode() : 0);
//        result = 31 * result + (on != null ? on.hashCode() : 0);
        return result;
    }
}
