package com.clocked.worktimecalculator.entities;

import java.time.temporal.ChronoUnit;

public class TimeInterval {
  private TimeRecord initialRecord;
  private TimeRecord endRecord;

  public TimeInterval(TimeRecord initialRecord, TimeRecord endRecord) throws Exception {
    if (endRecord.getDateTime().isBefore(initialRecord.getDateTime())) {
      throw new Exception();
    }
    this.endRecord = endRecord;
    this.initialRecord = initialRecord;
  }

  public TimeRecord getInitialRecord() {
    return initialRecord;
  }

  public TimeRecord getEndRecord() {
    return endRecord;
  }

  public double durationInSeconds() {
    return (double)
        ChronoUnit.SECONDS.between(initialRecord.getDateTime(), endRecord.getDateTime());
  }

  public double durationInHours() {
    return (double) durationInSeconds() / (60 * 60);
  }

  public TimeIntervalType type() {
    return type(false);
  }

  public TimeIntervalType type(boolean previousRegisteredRecordWasInDirected) {
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

    // if the initial record is registered out by the worker then the whole interval
    // is a work interval
    if (initialRecordType == TimeRecordType.REGISTERED
        && initialRecordDirection == TimeRecordDirection.OUT) {
      return TimeIntervalType.ABSENT;
    }

    // if the end record is registered in by the worker then the whole interval
    // is a absent interval
    if (endRecordType == TimeRecordType.REGISTERED
        && endRecordDirection == TimeRecordDirection.IN) {
      return TimeIntervalType.ABSENT;
    }

    // if the the interval begins and ends with shift records it may be a work interval
    // only if there are any work record before the current interval
    if ((initialRecordType == TimeRecordType.SHIFT
            && initialRecordDirection == TimeRecordDirection.IN)
        && (endRecordType == TimeRecordType.SHIFT
            && endRecordDirection == TimeRecordDirection.OUT)) {
      if (previousRegisteredRecordWasInDirected) {
        return TimeIntervalType.WORK;
      }
      return TimeIntervalType.ABSENT;
    }

    // Default return
    return TimeIntervalType.ABSENT;
  }
}
