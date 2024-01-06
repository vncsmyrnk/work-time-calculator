package com.clocked.worktimecalculator.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true; // Reflexivity
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false; // Symmetry and null check
    }
    Day otherDay = (Day) obj;
    return date.equals(otherDay.getDate())
        && timeIntervals.equals(otherDay.getTimeIntervals())
        && calculations.equals(otherDay.getCalculations());
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, timeIntervals, calculations);
  }
}
