package com.clocked.worktimecalculator.entities;

public class Calculation {
  private CalculationType type;
  private double value;

  public Calculation(CalculationType type, double value) {
    this.type = type;
    this.value = value;
  }

  public CalculationType getType() {
    return this.type;
  }

  public double getValue() {
    return this.value;
  }
}
