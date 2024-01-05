package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class DayTest {
  @Test
  void testCreateDay() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    List<TimeInterval> timeIntervals = new ArrayList<>();
    Day day = new Day(date, timeIntervals);
    assertEquals(date, day.getDate());
    assertEquals(timeIntervals, day.getTimeIntervals());
  }

  @Test
  void testDayCalculation() {
    List<TimeInterval> timeIntervals = new ArrayList<>();
    timeIntervals.add(
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 8, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 12, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.OUT)));

    timeIntervals.add(
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 13, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 17, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.OUT)));

    Day day = new Day(LocalDate.of(2023, 1, 1), timeIntervals);
    day.calculate();

    assertEquals(8, day.getCalculation(CalculationType.WORK).getValue());
    assertEquals(0, day.getCalculation(CalculationType.ABSENT).getValue());
  }
}
