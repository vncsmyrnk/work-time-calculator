package com.clocked.worktimecalculator.entities;

import java.time.temporal.ChronoUnit;

public class TimeInterval {
  private TimeRecord initialRecord;
  private TimeRecord endRecord;
  private boolean previousRegisteredRecordWasInDirected;

  public TimeInterval(TimeRecord initialRecord, TimeRecord endRecord) {
    if (areRecordsValid(initialRecord, endRecord)) {
      throw new IllegalArgumentException("Initial date should be greater than the final");
    }
    this.endRecord = endRecord;
    this.initialRecord = initialRecord;
    this.previousRegisteredRecordWasInDirected = false;
  }

  public TimeInterval(
      TimeRecord initialRecord,
      TimeRecord endRecord,
      boolean previousRegisteredRecordWasInDirected) {
    this(initialRecord, endRecord);
    this.previousRegisteredRecordWasInDirected = previousRegisteredRecordWasInDirected;
  }

  public TimeRecord getInitialRecord() {
    return initialRecord;
  }

  public TimeRecord getEndRecord() {
    return endRecord;
  }

  public double durationInSeconds() {
    return ChronoUnit.SECONDS.between(initialRecord.getDateTime(), endRecord.getDateTime());
  }

  public double durationInHours() {
    return durationInSeconds() / (60 * 60);
  }

  public TimeIntervalType type() {
    if (durationInSeconds() == 0) {
      return TimeIntervalType.NO_TIME;
    }

    TimeRecordType initialRecordType = initialRecord.getType();
    TimeRecordDirection initialRecordDirection = initialRecord.getDirection();

    TimeRecordType endRecordType = endRecord.getType();
    TimeRecordDirection endRecordDirection = endRecord.getDirection();

    // if the initial record is registered in by the worker then the whole interval
    // is a work interval
    if (initialRecordType == TimeRecordType.REGISTERED
        && initialRecordDirection == TimeRecordDirection.IN) {
      return TimeIntervalType.WORK;
    }

    // if the end record is registered out by the worker then the whole interval
    // is a work interval
    if (endRecordType == TimeRecordType.REGISTERED
        && endRecordDirection == TimeRecordDirection.OUT) {
      return TimeIntervalType.WORK;
    }

    // if the interval begins and ends with shift records it may be a work interval
    // only if there are any work record before the current interval
    if (initialRecordType == TimeRecordType.SHIFT
        && endRecordType == TimeRecordType.SHIFT
        && previousRegisteredRecordWasInDirected) {
      return TimeIntervalType.WORK;
    }

    // Determine absence when work is not detected
    return TimeIntervalType.ABSENT;
  }

  private boolean areRecordsValid(TimeRecord initialRecord, TimeRecord endRecord) {
    return endRecord.getDateTime().isBefore(initialRecord.getDateTime());
  }
}
