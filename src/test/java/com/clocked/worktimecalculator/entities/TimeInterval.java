package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class TimeIntervalTest {
  @Test
  void testCreateTimeInterval() throws Exception {
    assertThrows(
        Exception.class,
        () -> {
          TimeRecord recordA =
              new TimeRecord(
                  LocalDateTime.of(2023, 1, 1, 8, 0),
                  TimeRecordType.REGISTERED,
                  TimeRecordDirection.IN);
          TimeRecord recordB =
              new TimeRecord(
                  LocalDateTime.of(2023, 1, 1, 9, 0),
                  TimeRecordType.REGISTERED,
                  TimeRecordDirection.OUT);

          new TimeInterval(recordB, recordA);
        });
  }

  @Test
  void testCreateWorkTimeInterval() throws Exception {
    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 9, 0), TimeRecordType.REGISTERED, TimeRecordDirection.OUT);

    TimeInterval timeInterval = new TimeInterval(recordA, recordB);
    assertEquals(1, timeInterval.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval.type(false));
  }

  @Test
  void testCreateAbsentTimeInterval() throws Exception {
    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.OUT);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 10, 30),
            TimeRecordType.REGISTERED,
            TimeRecordDirection.IN);

    TimeInterval timeInterval = new TimeInterval(recordA, recordB);
    assertEquals(2.5, timeInterval.durationInHours());
    assertEquals(TimeIntervalType.ABSENT, timeInterval.type(false));
  }

  @Test
  void testCreateNoTimeTimeInterval() throws Exception {
    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.OUT);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);

    TimeInterval timeInterval = new TimeInterval(recordA, recordB);
    assertEquals(0, timeInterval.durationInHours());
    assertEquals(TimeIntervalType.NO_TIME, timeInterval.type());
  }

  @Test
  void testCreateTimeIntervalWorkDay() throws Exception {
    TimeRecord recordShiftA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.SHIFT, TimeRecordDirection.IN);
    TimeRecord recordShiftB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 17, 0), TimeRecordType.SHIFT, TimeRecordDirection.OUT);

    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 7, 50), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 17, 5),
            TimeRecordType.REGISTERED,
            TimeRecordDirection.OUT);

    TimeInterval timeInterval1 = new TimeInterval(recordA, recordShiftA);
    assertEquals(10.0 / 60.0, timeInterval1.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval1.type());

    TimeInterval timeInterval2 = new TimeInterval(recordShiftA, recordShiftB);
    assertEquals(9, timeInterval2.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval2.type(true));

    TimeInterval timeInterval3 = new TimeInterval(recordShiftB, recordB);
    assertEquals(5.0 / 60.0, timeInterval3.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval3.type());
  }
}
