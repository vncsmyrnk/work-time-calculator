package com.clocked.worktimecalculator.entities;

import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    Calculation otherCalculation = (Calculation) obj;
    return type.equals(otherCalculation.getType()) && value == otherCalculation.getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }
}
