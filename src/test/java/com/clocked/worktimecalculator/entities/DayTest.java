package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  @Test
  void testDayEquals() {
    Day dayA =
        new Day(
            LocalDate.of(2023, 1, 1),
            new ArrayList<>() {
              {
                add(
                    new TimeInterval(
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED),
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 10, 0), TimeRecordType.REGISTERED)));
              }
            });
    Day dayB =
        new Day(
            LocalDate.of(2023, 1, 1),
            new ArrayList<>() {
              {
                add(
                    new TimeInterval(
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED),
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 10, 0), TimeRecordType.REGISTERED)));
              }
            });
    assertEquals(true, dayA.equals(dayB));
  }

  @Test
  void testDayNotEquals() {
    Day dayA =
        new Day(
            LocalDate.of(2023, 1, 1),
            new ArrayList<>() {
              {
                add(
                    new TimeInterval(
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 9, 30), TimeRecordType.REGISTERED),
                        new TimeRecord(LocalDateTime.of(2023, 1, 1, 10, 0), TimeRecordType.SHIFT)));
              }
            });
    Day dayB =
        new Day(
            LocalDate.of(2023, 1, 2),
            new ArrayList<>() {
              {
                add(
                    new TimeInterval(
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED),
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 10, 0), TimeRecordType.REGISTERED)));
              }
            });
    assertNotEquals(true, dayA.equals(dayB));
  }

  @Test
  void testDayHashCode() {
    Day day =
        new Day(
            LocalDate.of(2023, 1, 1),
            new ArrayList<>() {
              {
                add(
                    new TimeInterval(
                        new TimeRecord(
                            LocalDateTime.of(2023, 1, 1, 9, 30), TimeRecordType.REGISTERED),
                        new TimeRecord(LocalDateTime.of(2023, 1, 1, 10, 0), TimeRecordType.SHIFT)));
              }
            });
    assertEquals(
        Objects.hash(day.getDate(), day.getTimeIntervals(), day.getCalculations()), day.hashCode());
  }
}
