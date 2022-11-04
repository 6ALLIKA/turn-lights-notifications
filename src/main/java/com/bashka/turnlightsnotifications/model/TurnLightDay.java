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
public class TurnLightDay {
    String groupNumber;
    List<TurnLightTimeRange> off;
    List<TurnLightTimeRange> on;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurnLightDay that = (TurnLightDay) o;

        if (!Objects.equals(groupNumber, that.groupNumber)) return false;
        if (!Objects.equals(off, that.off)) return false;
        return Objects.equals(on, that.on);
    }

    @Override
    public int hashCode() {
        int result = groupNumber != null ? groupNumber.hashCode() : 0;
        result = 31 * result + (off != null ? off.hashCode() : 0);
        result = 31 * result + (on != null ? on.hashCode() : 0);
        return result;
    }
}
