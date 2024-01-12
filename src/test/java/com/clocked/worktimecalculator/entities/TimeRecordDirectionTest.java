package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeRecordDirectionTest {
  @Test
  void testIsIn() {
    TimeRecordDirection direction = TimeRecordDirection.IN;
    assertEquals(true, direction.isIn());

    direction = TimeRecordDirection.OUT;
    assertEquals(false, direction.isIn());
  }

  @Test
  void testIsNotIn() {
    TimeRecordDirection direction = TimeRecordDirection.IN;
    assertEquals(false, direction.isNotIn());

    direction = TimeRecordDirection.OUT;
    assertEquals(true, direction.isNotIn());
  }

  void testIsOut() {
    TimeRecordDirection direction = TimeRecordDirection.OUT;
    assertEquals(true, direction.isOut());

    direction = TimeRecordDirection.IN;
    assertEquals(false, direction.isOut());
  }

  @Test
  void testIsNotOut() {
    TimeRecordDirection direction = TimeRecordDirection.OUT;
    assertEquals(false, direction.isNotOut());

    direction = TimeRecordDirection.IN;
    assertEquals(true, direction.isNotOut());
  }

  @Test
  void testToggle() {
    TimeRecordDirection direction = TimeRecordDirection.OUT;
    assertEquals(TimeRecordDirection.IN, direction.toggle());
    assertEquals(TimeRecordDirection.OUT, direction);
  }
}
