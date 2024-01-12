package com.clocked.worktimecalculator.entities;

public enum TimeRecordDirection {
  IN,
  OUT;

  public boolean isIn() {
    return this == IN;
  }

  public boolean isNotIn() {
    return !isIn();
  }

  public boolean isOut() {
    return this == OUT;
  }

  public boolean isNotOut() {
    return !isOut();
  }

  public TimeRecordDirection toggle() {
    return this == IN ? OUT : IN;
  }
}
