package com.clocked.worktimecalculator.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Day {
  private LocalDate date;
  private List<Calculation> calculations;
  private List<TimeInterval> timeIntervals;

  public Day(LocalDate date, List<TimeInterval> timeIntervals) {
    this.date = date;
    this.calculations = new ArrayList<>();
    this.timeIntervals = timeIntervals;
  }

  public List<Calculation> calculate() {
    calculateHoursWorkTime();
    calculateHoursAbsentTime();
    return calculations;
  }

  public LocalDate getDate() {
    return date;
  }

  public List<Calculation> getCalculations() {
    return calculations;
  }

  public List<TimeInterval> getTimeIntervals() {
    return timeIntervals;
  }

  public Calculation getCalculation(CalculationType type) {
    return calculations.stream().filter(c -> c.getType() == type).findFirst().orElse(null);
  }

  private void calculateHoursWorkTime() {
    double workTime =
        timeIntervals.stream()
            .filter(t -> t.type() == TimeIntervalType.WORK)
            .mapToDouble(TimeInterval::durationInHours)
            .sum();
    this.calculations.add(new Calculation(CalculationType.WORK, workTime));
  }

  private void calculateHoursAbsentTime() {
    double absentTime =
        timeIntervals.stream()
            .filter(t -> t.type() == TimeIntervalType.ABSENT)
            .mapToDouble(TimeInterval::durationInHours)
            .sum();
    this.calculations.add(new Calculation(CalculationType.ABSENT, absentTime));
  }
}
