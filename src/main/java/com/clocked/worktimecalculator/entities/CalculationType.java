package com.clocked.worktimecalculator.entities;

public enum CalculationType {
  WORK,
  ABSENT;

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
}
