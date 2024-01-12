package com.clocked.worktimecalculator.entities;

public enum TimeIntervalType {
  WORK,
  ABSENT,
  NO_TIME;

  public boolean isWork() {
    return this == WORK;
  }

  public boolean isNotWork() {
    return !isWork();
  }

  public boolean isAbsent() {
    return this == ABSENT;
  }

  public boolean isNotAbsent() {
    return !isAbsent();
  }

  public boolean isNoTime() {
    return this == NO_TIME;
  }

  public boolean isNotNoTime() {
    return !isNoTime();
  }
}
