package com.clocked.worktimecalculator.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.clocked.worktimecalculator.entities.TimeRecord;
import com.clocked.worktimecalculator.entities.TimeRecordType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class TimeRecordBuilderTest {
  @Test
  void testCreationTimeRecords() {
    List<LocalDateTime> dateTimes =
        new ArrayList<>() {
          {
            add(LocalDateTime.of(2023, 1, 1, 8, 0));
            add(LocalDateTime.of(2023, 1, 1, 12, 0));
            add(LocalDateTime.of(2023, 1, 1, 13, 0));
            add(LocalDateTime.of(2023, 1, 1, 17, 0));
          }
        };

    TimeRecordType typeExpected = TimeRecordType.REGISTERED;

    List<TimeRecord> timeRecordsExpected =
        new ArrayList<>() {
          {
            add(new TimeRecord(dateTimes.get(0), typeExpected));
            add(new TimeRecord(dateTimes.get(1), typeExpected));
            add(new TimeRecord(dateTimes.get(2), typeExpected));
            add(new TimeRecord(dateTimes.get(3), typeExpected));
          }
        };

    List<TimeRecord> actual = TimeRecordBuilder.of(dateTimes, typeExpected);
    assertEquals(timeRecordsExpected, actual);
  }
}
