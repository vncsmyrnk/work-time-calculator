package com.clocked.worktimecalculator.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeRecord implements Comparable<TimeRecord> {
  private LocalDateTime dateTime;
  private TimeRecordType type;
  private TimeRecordDirection direction;

  public TimeRecord(LocalDateTime dateTime, TimeRecordType type) {
    this.dateTime = dateTime;
    this.type = type;
    this.direction = null;
  }

  public TimeRecord(LocalDateTime dateTime, TimeRecordType type, TimeRecordDirection direction) {
    this(dateTime, type);
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

  public TimeRecord setDirection(TimeRecordDirection direction) {
    this.direction = direction;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    TimeRecord otherTimeRecord = (TimeRecord) obj;
    return dateTime.equals(otherTimeRecord.getDateTime())
        && type == otherTimeRecord.getType()
        && direction == otherTimeRecord.getDirection();
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateTime, type, direction);
  }

  @Override
  public int compareTo(TimeRecord otherTimeRecord) {
    return dateTime.compareTo(otherTimeRecord.getDateTime());
  }
}
