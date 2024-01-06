package com.clocked.worktimecalculator.builder;

import com.clocked.worktimecalculator.entities.TimeRecord;
import com.clocked.worktimecalculator.entities.TimeRecordType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeRecordBuilder {
  private TimeRecordBuilder() {}

  public static List<TimeRecord> of(List<LocalDateTime> dateTimes, TimeRecordType type) {
    List<TimeRecord> timeRecords = new ArrayList<>();
    dateTimes.forEach(
        dateTime -> {
          timeRecords.add(new TimeRecord(dateTime, type));
        });
    return timeRecords;
  }
}
