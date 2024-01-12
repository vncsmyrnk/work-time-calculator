package com.clocked.worktimecalculator.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.clocked.worktimecalculator.entities.TimeInterval;
import com.clocked.worktimecalculator.entities.TimeRecord;
import com.clocked.worktimecalculator.entities.TimeRecordDirection;
import com.clocked.worktimecalculator.entities.TimeRecordType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class TimeIntervalBuilderTest {
  @Test
  void testTimeIntervalCreationByTimeRecords() {
    List<TimeRecord> timeRecords =
        new ArrayList<>() {
          {
            add(
                new TimeRecord(
                    LocalDateTime.of(2023, 1, 1, 8, 0),
                    TimeRecordType.REGISTERED,
                    TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    LocalDateTime.of(2023, 1, 1, 12, 0),
                    TimeRecordType.REGISTERED,
                    TimeRecordDirection.OUT));
            add(
                new TimeRecord(
                    LocalDateTime.of(2023, 1, 1, 13, 0),
                    TimeRecordType.REGISTERED,
                    TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    LocalDateTime.of(2023, 1, 1, 17, 0),
                    TimeRecordType.REGISTERED,
                    TimeRecordDirection.OUT));
          }
        };

    List<TimeInterval> timeIntervalsExpected =
        new ArrayList<>() {
          {
            add(new TimeInterval(timeRecords.get(0), timeRecords.get(1)));
            add(new TimeInterval(timeRecords.get(1), timeRecords.get(2)));
            add(new TimeInterval(timeRecords.get(2), timeRecords.get(3), true));
          }
        };

    List<TimeInterval> actual = TimeIntervalBuilder.of(timeRecords);
    assertEquals(timeIntervalsExpected, actual);
  }

  @Test
  void testTimeIntervalCreationByDateTimes() {
    List<LocalDateTime> dateTimes =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 7, 55));
            add(LocalDateTime.of(2023, 1, 1, 17, 23));
            add(LocalDateTime.of(2023, 1, 1, 12, 2));
            add(LocalDateTime.of(2023, 1, 1, 13, 5));
          }
        };

    List<LocalDateTime> dateTimesShift =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 8, 0));
            add(LocalDateTime.of(2023, 1, 1, 12, 0));
            add(LocalDateTime.of(2023, 1, 1, 17, 0));
            add(LocalDateTime.of(2023, 1, 1, 13, 0));
          }
        };

    List<TimeRecord> allTimeRecords =
        new ArrayList<>() {
          {
            add(
                new TimeRecord(
                    dateTimes.get(0), TimeRecordType.REGISTERED, TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    dateTimesShift.get(0), TimeRecordType.SHIFT, TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    dateTimesShift.get(1), TimeRecordType.SHIFT, TimeRecordDirection.OUT));
            add(
                new TimeRecord(
                    dateTimes.get(2), TimeRecordType.REGISTERED, TimeRecordDirection.OUT));
            add(
                new TimeRecord(
                    dateTimesShift.get(3), TimeRecordType.SHIFT, TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    dateTimes.get(3), TimeRecordType.REGISTERED, TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    dateTimesShift.get(2), TimeRecordType.SHIFT, TimeRecordDirection.OUT));
            add(
                new TimeRecord(
                    dateTimes.get(1), TimeRecordType.REGISTERED, TimeRecordDirection.OUT));
          }
        };

    List<TimeInterval> timeIntervalsExpected =
        new ArrayList<>() {
          {
            add(new TimeInterval(allTimeRecords.get(0), allTimeRecords.get(1)));
            add(new TimeInterval(allTimeRecords.get(1), allTimeRecords.get(2), true));
            add(new TimeInterval(allTimeRecords.get(2), allTimeRecords.get(3), true));
            add(new TimeInterval(allTimeRecords.get(3), allTimeRecords.get(4)));
            add(new TimeInterval(allTimeRecords.get(4), allTimeRecords.get(5)));
            add(new TimeInterval(allTimeRecords.get(5), allTimeRecords.get(6), true));
            add(new TimeInterval(allTimeRecords.get(6), allTimeRecords.get(7), true));
          }
        };

    List<TimeInterval> actual = TimeIntervalBuilder.of(dateTimes, dateTimesShift);
    assertEquals(timeIntervalsExpected, actual);
  }

  @Test
  void testAnotherTimeIntervalCreationByDateTimes() {
    List<LocalDateTime> dateTimes =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 8, 0));
            add(LocalDateTime.of(2023, 1, 1, 16, 0));
          }
        };

    List<LocalDateTime> dateTimesShift =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 12, 0));
            add(LocalDateTime.of(2023, 1, 1, 13, 0));
          }
        };

    List<TimeRecord> allTimeRecords =
        new ArrayList<>() {
          {
            add(
                new TimeRecord(
                    dateTimes.get(0), TimeRecordType.REGISTERED, TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    dateTimesShift.get(0), TimeRecordType.SHIFT, TimeRecordDirection.IN));
            add(
                new TimeRecord(
                    dateTimesShift.get(1), TimeRecordType.SHIFT, TimeRecordDirection.OUT));
            add(
                new TimeRecord(
                    dateTimes.get(1), TimeRecordType.REGISTERED, TimeRecordDirection.OUT));
          }
        };

    List<TimeInterval> timeIntervalsExpected =
        new ArrayList<>() {
          {
            add(new TimeInterval(allTimeRecords.get(0), allTimeRecords.get(1)));
            add(new TimeInterval(allTimeRecords.get(1), allTimeRecords.get(2), true));
            add(new TimeInterval(allTimeRecords.get(2), allTimeRecords.get(3), true));
          }
        };

    List<TimeInterval> actual = TimeIntervalBuilder.of(dateTimes, dateTimesShift);
    assertEquals(timeIntervalsExpected, actual);
  }
}
