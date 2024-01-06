package com.clocked.worktimecalculator.builder;

import com.clocked.worktimecalculator.entities.Day;
import com.clocked.worktimecalculator.entities.TimeInterval;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DayBuilder {
  private DayBuilder() {}

  public static Day of(
      LocalDate date, List<LocalDateTime> registeredRecords, List<LocalDateTime> shiftRecords) {
    List<TimeInterval> timeIntervals = TimeIntervalBuilder.of(registeredRecords, shiftRecords);
    return new Day(date, timeIntervals);
  }
}
