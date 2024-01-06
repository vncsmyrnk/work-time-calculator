package com.clocked.worktimecalculator.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.clocked.worktimecalculator.entities.Day;
import com.clocked.worktimecalculator.entities.TimeInterval;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class DayBuilderTest {
  @Test
  void testCreateDay() {
    List<LocalDateTime> dateTimes =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 7, 55));
            add(LocalDateTime.of(2023, 1, 1, 12, 2));
            add(LocalDateTime.of(2023, 1, 1, 13, 5));
            add(LocalDateTime.of(2023, 1, 1, 17, 23));
          }
        };

    List<LocalDateTime> dateTimesShift =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 8, 0));
            add(LocalDateTime.of(2023, 1, 1, 12, 0));
            add(LocalDateTime.of(2023, 1, 1, 13, 0));
            add(LocalDateTime.of(2023, 1, 1, 17, 0));
          }
        };

    List<TimeInterval> timeIntervalsExpected = TimeIntervalBuilder.of(dateTimes, dateTimesShift);
    LocalDate dateExpected = LocalDate.of(2023, 1, 1);
    Day day = DayBuilder.of(dateExpected, dateTimes, dateTimesShift);

    assertEquals(dateExpected, day.getDate());
    assertEquals(timeIntervalsExpected, day.getTimeIntervals());
  }
}
