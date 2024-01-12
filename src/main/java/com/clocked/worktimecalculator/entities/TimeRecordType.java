package com.clocked.worktimecalculator.entities;

public enum TimeRecordType {
  SHIFT,
  REGISTERED;

  public boolean isShift() {
    return this == SHIFT;
  }

  public boolean isNotShift() {
    return !isShift();
  }

  public boolean isRegistered() {
    return this == REGISTERED;
  }

  public boolean isNotRegistered() {
    return !isRegistered();
  }
}
