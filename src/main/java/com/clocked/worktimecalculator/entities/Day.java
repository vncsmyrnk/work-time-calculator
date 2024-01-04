package com.clocked.worktimecalculator.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Day {
  private LocalDateTime date;
  private ArrayList<Calculation> calculations;
  private ArrayList<TimeInterval> timeIntervals;

  public Day(LocalDateTime date, ArrayList<TimeInterval> timeIntervals) {
    this.date = date;
    this.calculations = new ArrayList<>();
    this.timeIntervals = timeIntervals;
  }

  public ArrayList<Calculation> calculate() {
    calculateWorkTime();
    calculateAbsentTime();
    return calculations;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public ArrayList<Calculation> getCalculations() {
    return calculations;
  }

  public ArrayList<TimeInterval> getTimeIntervals() {
    return timeIntervals;
  }

  private void calculateWorkTime() {
    this.calculations.add(new Calculation(CalculationType.WORK, 8));
  }

  private void calculateAbsentTime() {
    this.calculations.add(new Calculation(CalculationType.ABSENT, 0));
  }
}
