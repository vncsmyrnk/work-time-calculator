package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class TimeIntervalTest {
  @Test
  void testCreateTimeInterval() {
    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 9, 0), TimeRecordType.REGISTERED, TimeRecordDirection.OUT);

    TimeInterval timeInterval = new TimeInterval(recordA, recordB);
    assertEquals(recordA, timeInterval.getInitialRecord());
    assertEquals(recordB, timeInterval.getEndRecord());
    assertEquals(false, timeInterval.getPreviousRegisteredRecordWasInDirected());
  }

  @Test
  void testCreateTimeIntervalValidations() {
    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 9, 0), TimeRecordType.REGISTERED, TimeRecordDirection.OUT);

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new TimeInterval(recordB, recordA);
        });
  }

  @Test
  void testCreateWorkTimeInterval() {
    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 9, 0), TimeRecordType.REGISTERED, TimeRecordDirection.OUT);

    TimeInterval timeInterval = new TimeInterval(recordA, recordB);
    assertEquals(1, timeInterval.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval.type());
  }

  @Test
  void testCreateAbsentTimeInterval() {
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
    assertEquals(TimeIntervalType.ABSENT, timeInterval.type());
  }

  @Test
  void testCreateNoTimeTimeInterval() {
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
  void testCreateTimeIntervalWorkDay() {
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

    TimeInterval timeInterval2 = new TimeInterval(recordShiftA, recordShiftB, true);
    assertEquals(9, timeInterval2.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval2.type());

    TimeInterval timeInterval3 = new TimeInterval(recordShiftB, recordB);
    assertEquals(5.0 / 60.0, timeInterval3.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval3.type());
  }

  @Test
  void testCreateTimeUnlikelyIntervalWorkDay() {
    TimeRecord recordShiftA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 4, 0), TimeRecordType.SHIFT, TimeRecordDirection.IN);
    TimeRecord recordShiftB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 12, 0), TimeRecordType.SHIFT, TimeRecordDirection.OUT);

    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 14, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 22, 0),
            TimeRecordType.REGISTERED,
            TimeRecordDirection.OUT);

    TimeInterval timeInterval1 = new TimeInterval(recordShiftA, recordShiftB);
    assertEquals(8, timeInterval1.durationInHours());
    assertEquals(TimeIntervalType.ABSENT, timeInterval1.type());

    TimeInterval timeInterval2 = new TimeInterval(recordShiftB, recordA, true);
    assertEquals(2, timeInterval2.durationInHours());
    assertEquals(TimeIntervalType.ABSENT, timeInterval2.type());

    TimeInterval timeInterval3 = new TimeInterval(recordA, recordB);
    assertEquals(8, timeInterval3.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval3.type());
  }

  @Test
  void testCreateTimeIntervalWorkDay2() {
    TimeRecord recordShiftA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.SHIFT, TimeRecordDirection.IN);
    TimeRecord recordShiftB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 17, 0), TimeRecordType.SHIFT, TimeRecordDirection.OUT);

    TimeRecord recordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 8, 10), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord recordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 17, 15),
            TimeRecordType.REGISTERED,
            TimeRecordDirection.OUT);

    TimeInterval timeInterval1 = new TimeInterval(recordShiftA, recordA);
    assertEquals(10.0 / 60.0, timeInterval1.durationInHours());
    assertEquals(TimeIntervalType.ABSENT, timeInterval1.type());

    TimeInterval timeInterval2 = new TimeInterval(recordA, recordShiftB);
    assertEquals(8 + 50.0 / 60.0, timeInterval2.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval2.type());

    TimeInterval timeInterval3 = new TimeInterval(recordShiftB, recordB);
    assertEquals(15.0 / 60.0, timeInterval3.durationInHours());
    assertEquals(TimeIntervalType.WORK, timeInterval3.type());
  }

  @Test
  void testTimeIntervalEquals() {
    TimeInterval timeIntervalA =
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 8, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 9, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.OUT));
    TimeInterval timeIntervalB =
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 8, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 9, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.OUT));
    assertEquals(true, timeIntervalA.equals(timeIntervalB));
    assertEquals(true, timeIntervalA.equals(timeIntervalA));
  }

  @Test
  void testTimeIntervalNotEquals() {
    TimeInterval timeIntervalA =
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.SHIFT, TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 10, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN));
    TimeInterval timeIntervalB =
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 8, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 9, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.OUT));
    assertNotEquals(true, timeIntervalA.equals(timeIntervalB));
    assertNotEquals(true, timeIntervalA.equals(LocalDate.of(2023, 1, 1)));
    assertNotEquals(true, timeIntervalA.equals(null));
  }

  @Test
  void testTimeIntervalHashCode() {
    TimeInterval timeInterval =
        new TimeInterval(
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 8, 0), TimeRecordType.SHIFT, TimeRecordDirection.IN),
            new TimeRecord(
                LocalDateTime.of(2023, 1, 1, 10, 0),
                TimeRecordType.REGISTERED,
                TimeRecordDirection.IN));
    assertEquals(
        Objects.hash(timeInterval.getInitialRecord(), timeInterval.getEndRecord()),
        timeInterval.hashCode());
  }
}
