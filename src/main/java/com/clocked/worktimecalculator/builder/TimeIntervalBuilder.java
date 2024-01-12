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
    Collections.sort(timeRecords);
    setTimeRecordsDirection(timeRecords);
    return buildTimeIntervals(timeRecords);
  }

  private static List<TimeInterval> buildTimeIntervals(List<TimeRecord> orderedTimeRecords) {
    List<TimeInterval> timeIntervals = new ArrayList<>();
    Iterator<Pair<TimeRecord, TimeRecord>> iterator = new TwoElementsIterator<>(orderedTimeRecords);
    boolean previousRegisteredRecordWasInDirected = false;

    while (iterator.hasNext()) {
      Pair<TimeRecord, TimeRecord> dateTimePair = iterator.next();
      timeIntervals.add(
          new TimeInterval(
              dateTimePair.first, dateTimePair.second, previousRegisteredRecordWasInDirected));

      boolean secondDateTimeIsRegisteredAndOut =
          dateTimePair.second.getType().isRegistered()
              && dateTimePair.second.getDirection().isOut();

      boolean firstDateTimeIsRegisteredAndOutAndSecondIsNotRegistered =
          dateTimePair.first.getType().isRegistered()
              && dateTimePair.first.getDirection().isOut()
              && dateTimePair.second.getType().isNotRegistered();

      if (secondDateTimeIsRegisteredAndOut
          || firstDateTimeIsRegisteredAndOutAndSecondIsNotRegistered) {
        previousRegisteredRecordWasInDirected = false;
        continue;
      }

      boolean secondDateTimeIsRegisteredAndIn =
          dateTimePair.second.getType().isRegistered() && dateTimePair.second.getDirection().isIn();

      boolean firstDateTimeIsRegisteredAndInAndSecondIsNotRegistered =
          dateTimePair.first.getType().isRegistered()
              && dateTimePair.first.getDirection().isIn()
              && dateTimePair.second.getType().isNotRegistered();

      if (secondDateTimeIsRegisteredAndIn
          || firstDateTimeIsRegisteredAndInAndSecondIsNotRegistered) {
        previousRegisteredRecordWasInDirected = true;
      }
    }

    return timeIntervals;
  }

  private static List<TimeRecord> setTimeRecordsDirection(List<TimeRecord> orderedTimeRecords) {
    Iterator<TimeRecord> timeRecordIterator = orderedTimeRecords.iterator();
    TimeRecordDirection lastRegisteredDirection = TimeRecordDirection.OUT;
    TimeRecordDirection lastShiftDirection = TimeRecordDirection.OUT;

    while (timeRecordIterator.hasNext()) {
      TimeRecord timeRecord = timeRecordIterator.next();

      if (timeRecord.getType() == TimeRecordType.REGISTERED) {
        lastRegisteredDirection = lastRegisteredDirection.toggle();
        timeRecord.setDirection(lastRegisteredDirection);
      }

      if (timeRecord.getType() == TimeRecordType.SHIFT) {
        lastShiftDirection = lastShiftDirection.toggle();
        timeRecord.setDirection(lastShiftDirection);
      }
    }

    return orderedTimeRecords;
  }
}
