package com.clocked.worktimecalculator.entities;

import java.time.LocalDateTime;

public class TimeRecord {
  private LocalDateTime dateTime;

  public TimeRecord(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public LocalDateTime getDateTime() {
    return this.dateTime;
  }
}
