package com.clocked.worktimecalculator.builder;

import com.clocked.worktimecalculator.entities.TimeInterval;
import com.clocked.worktimecalculator.entities.TimeRecord;
import com.clocked.worktimecalculator.entities.TimeRecordDirection;
import com.clocked.worktimecalculator.entities.TimeRecordType;
import com.clocked.worktimecalculator.util.Pair;
import com.clocked.worktimecalculator.util.TwoElementsIterator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TimeIntervalBuilder {
  private TimeIntervalBuilder() {}

  public static List<TimeInterval> of(
      List<LocalDateTime> registeredRecords, List<LocalDateTime> shiftRecords) {
    List<TimeRecord> timeRecords =
        TimeRecordBuilder.of(registeredRecords, TimeRecordType.REGISTERED);
    List<TimeRecord> shiftTimeRecords = TimeRecordBuilder.of(shiftRecords, TimeRecordType.SHIFT);
    timeRecords.addAll(shiftTimeRecords);
    return of(timeRecords);
  }

  public static List<TimeInterval> of(List<TimeRecord> timeRecords) {
    // Sort the time record list
    Collections.sort(timeRecords);

    // Define the direction of the time records
    Iterator<TimeRecord> timeRecordIterator = timeRecords.iterator();
    TimeRecordDirection lastRegisteredDirection = TimeRecordDirection.OUT;
    TimeRecordDirection lastShiftDirection = TimeRecordDirection.OUT;

    while (timeRecordIterator.hasNext()) {
      TimeRecord timeRecord = timeRecordIterator.next();

      if (timeRecord.getType() == TimeRecordType.REGISTERED) {
        lastRegisteredDirection =
            lastRegisteredDirection == TimeRecordDirection.IN
                ? TimeRecordDirection.OUT
                : TimeRecordDirection.IN;

        timeRecord.setDirection(lastRegisteredDirection);
      }

      if (timeRecord.getType() == TimeRecordType.SHIFT) {
        lastShiftDirection =
            lastShiftDirection == TimeRecordDirection.IN
                ? TimeRecordDirection.OUT
                : TimeRecordDirection.IN;

        timeRecord.setDirection(lastShiftDirection);
      }
    }

    // Build the time intervals
    List<TimeInterval> timeIntervals = new ArrayList<>();
    Iterator<Pair<TimeRecord, TimeRecord>> iterator = new TwoElementsIterator<>(timeRecords);

    while (iterator.hasNext()) {
      Pair<TimeRecord, TimeRecord> dateTimePair = iterator.next();
      timeIntervals.add(new TimeInterval(dateTimePair.first, dateTimePair.second));

      // @TODO Add the previousRegisteredRecordWasInDirected rule
    }

    return timeIntervals;
  }
}
