package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class CalculationTest {
  @Test
  void testCreateCalculation() {
    Calculation calculation = new Calculation(CalculationType.WORK, 8);
    assertEquals(CalculationType.WORK, calculation.getType());
    assertEquals(8, calculation.getValue());
  }

  @Test
  void testCalculationEquals() {
    Calculation calculationA = new Calculation(CalculationType.WORK, 8);
    Calculation calculationB = new Calculation(CalculationType.WORK, 8);
    assertTrue(calculationA.equals(calculationB));
  }

  @Test
  void testCalculationNotEquals() {
    Calculation calculationA = new Calculation(CalculationType.WORK, 8);
    Calculation calculationB = new Calculation(CalculationType.ABSENT, 9);
    assertFalse(calculationA.equals(calculationB));
  }

  @Test
  void testCalculationHashCode() {
    Calculation calculation = new Calculation(CalculationType.WORK, 8);
    assertEquals(
        Objects.hash(calculation.getType(), calculation.getValue()), calculation.hashCode());
  }
}
