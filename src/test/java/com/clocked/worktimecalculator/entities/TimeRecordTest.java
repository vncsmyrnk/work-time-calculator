package com.clocked.worktimecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.clocked.worktimecalculator.entities.TimeRecord;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class TimeRecordTest {
  @Test
  void testCreateTimeRecord() {
    LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 0, 0);
    TimeRecord timeRecord = new TimeRecord(dateTime);
    assertEquals(dateTime, timeRecord.getDateTime());
  }
}
