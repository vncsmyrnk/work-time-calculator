package com.clocked.worktimecalculator.entities;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeInterval {
  private TimeRecord initialRecord;
  private TimeRecord finalRecord;
  private boolean workTimeIntervalOpenInAPreviousInterval;

  public TimeInterval(TimeRecord initialRecord, TimeRecord finalRecord) {
    if (initialRecord == null || finalRecord == null) {
      throw new IllegalArgumentException("Initial and final Time Records must be non-null");
    }
    if (areRecordsValid(initialRecord, finalRecord)) {
      throw new IllegalArgumentException("Initial date should be greater than the final");
    }
    this.finalRecord = finalRecord;
    this.initialRecord = initialRecord;
    this.workTimeIntervalOpenInAPreviousInterval = false;
  }

  public TimeInterval(
      TimeRecord initialRecord,
      TimeRecord finalRecord,
      boolean workTimeIntervalOpenInAPreviousInterval) {
    this(initialRecord, finalRecord);
    this.workTimeIntervalOpenInAPreviousInterval = workTimeIntervalOpenInAPreviousInterval;
  }

  public TimeRecord getInitialRecord() {
    return initialRecord;
  }

  public TimeRecord getFinalRecord() {
    return finalRecord;
  }

  public boolean getWorkTimeIntervalOpenInAPreviousInterval() {
    return workTimeIntervalOpenInAPreviousInterval;
  }

  public double durationInSeconds() {
    return ChronoUnit.SECONDS.between(initialRecord.getDateTime(), finalRecord.getDateTime());
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

    TimeRecordType finalRecordType = finalRecord.getType();
    TimeRecordDirection finalRecordDirection = finalRecord.getDirection();

    // if the initial record is registered in by the worker then the whole interval
    // is a work interval
    if (initialRecordType == TimeRecordType.REGISTERED
        && initialRecordDirection == TimeRecordDirection.IN) {
      return TimeIntervalType.WORK;
    }

    // if the end record is registered out by the worker then the whole interval
    // is a work interval
    if (finalRecordType == TimeRecordType.REGISTERED
        && finalRecordDirection == TimeRecordDirection.OUT) {
      return TimeIntervalType.WORK;
    }

    // if the interval begins and ends with shift records it may be a work interval
    // only if there are any work record before the current interval
    if (initialRecordType == TimeRecordType.SHIFT
        && finalRecordType == TimeRecordType.SHIFT
        && workTimeIntervalOpenInAPreviousInterval) {
      return TimeIntervalType.WORK;
    }

    // Determine absence when work is not detected
    return TimeIntervalType.ABSENT;
  }

  private boolean areRecordsValid(TimeRecord initialRecord, TimeRecord finalRecord) {
    return finalRecord.getDateTime().isBefore(initialRecord.getDateTime());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true; // Reflexivity
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false; // Symmetry and null check
    }
    TimeInterval otherTimeInterval = (TimeInterval) obj;
    return initialRecord.equals(otherTimeInterval.getInitialRecord())
        && finalRecord.equals(otherTimeInterval.getFinalRecord())
        && workTimeIntervalOpenInAPreviousInterval
            == otherTimeInterval.workTimeIntervalOpenInAPreviousInterval;
  }

  @Override
  public int hashCode() {
    return Objects.hash(initialRecord, finalRecord);
  }
}
