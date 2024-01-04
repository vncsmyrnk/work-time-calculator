package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculationTest {
  @Test
  void testCreateCalculation() {
    Calculation calculation = new Calculation(CalculationType.WORK, 8);
    assertEquals(CalculationType.WORK, calculation.getType());
    assertEquals(8, calculation.getValue());
  }
}
