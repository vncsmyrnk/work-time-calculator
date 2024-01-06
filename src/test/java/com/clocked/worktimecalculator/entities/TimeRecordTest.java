package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class TimeRecordTest {
  @Test
  void testCreateTimeRecord() {
    LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 0, 0);
    TimeRecord timeRecord =
        new TimeRecord(dateTime, TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    assertEquals(dateTime, timeRecord.getDateTime());
    assertEquals(TimeRecordType.REGISTERED, timeRecord.getType());
    assertEquals(TimeRecordDirection.IN, timeRecord.getDirection());
  }

  @Test
  void testTimeRecordEquals() {
    TimeRecord timeRecordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 0, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord timeRecordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 0, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    assertEquals(true, timeRecordA.equals(timeRecordB));
  }

  @Test
  void testTimeRecordNotEquals() {
    TimeRecord timeRecordA =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 0, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    TimeRecord timeRecordB =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 2, 0, 0), TimeRecordType.SHIFT, TimeRecordDirection.OUT);
    assertNotEquals(true, timeRecordA.equals(timeRecordB));
  }

  @Test
  void testTimeRecordHashCode() {
    TimeRecord timeRecord =
        new TimeRecord(
            LocalDateTime.of(2023, 1, 1, 0, 0), TimeRecordType.REGISTERED, TimeRecordDirection.IN);
    assertEquals(
        Objects.hash(timeRecord.getDateTime(), timeRecord.getType(), timeRecord.getDirection()),
        timeRecord.hashCode());
  }
}
