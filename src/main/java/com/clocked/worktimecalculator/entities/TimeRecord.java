package com.clocked.worktimecalculator.entities;

import java.time.LocalDateTime;

public class TimeRecord {
  private LocalDateTime dateTime;
  private TimeRecordType type;
  private TimeRecordDirection direction;

  public TimeRecord(LocalDateTime dateTime, TimeRecordType type, TimeRecordDirection direction) {
    this.dateTime = dateTime;
    this.type = type;
    this.direction = direction;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public TimeRecordType getType() {
    return type;
  }

  public TimeRecordDirection getDirection() {
    return direction;
  }
}
