package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
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
}
