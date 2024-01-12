package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculationTypeTest {
  @Test
  void testIsWork() {
    CalculationType type = CalculationType.WORK;
    assertEquals(true, type.isWork());

    type = CalculationType.ABSENT;
    assertEquals(false, type.isWork());
  }

  @Test
  void testIsNotWork() {
    CalculationType type = CalculationType.WORK;
    assertEquals(false, type.isNotWork());

    type = CalculationType.ABSENT;
    assertEquals(true, type.isNotWork());
  }

  @Test
  void testIsAbsent() {
    CalculationType type = CalculationType.ABSENT;
    assertEquals(true, type.isAbsent());

    type = CalculationType.WORK;
    assertEquals(false, type.isAbsent());
  }

  @Test
  void testIsNotAbsent() {
    CalculationType type = CalculationType.ABSENT;
    assertEquals(false, type.isNotAbsent());

    type = CalculationType.WORK;
    assertEquals(true, type.isNotAbsent());
  }
}
