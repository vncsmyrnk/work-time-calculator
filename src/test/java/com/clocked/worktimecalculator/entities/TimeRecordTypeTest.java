package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeRecordTypeTest {
  @Test
  void testIsRegistered() {
    TimeRecordType type = TimeRecordType.REGISTERED;
    assertEquals(true, type.isRegistered());

    type = TimeRecordType.SHIFT;
    assertEquals(false, type.isRegistered());
  }

  @Test
  void testIsNotRegistered() {
    TimeRecordType type = TimeRecordType.REGISTERED;
    assertEquals(false, type.isNotRegistered());

    type = TimeRecordType.SHIFT;
    assertEquals(true, type.isNotRegistered());
  }

  @Test
  void testIsShift() {
    TimeRecordType type = TimeRecordType.SHIFT;
    assertEquals(true, type.isShift());

    type = TimeRecordType.REGISTERED;
    assertEquals(false, type.isShift());
  }

  @Test
  void testIsNotShift() {
    TimeRecordType type = TimeRecordType.SHIFT;
    assertEquals(false, type.isNotShift());

    type = TimeRecordType.REGISTERED;
    assertEquals(true, type.isNotShift());
  }
}
