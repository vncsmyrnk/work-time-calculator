package com.clocked.worktimecalculator.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeIntervalTypeTest {
  @Test
  void testIsWork() {
    TimeIntervalType type = TimeIntervalType.WORK;
    assertEquals(true, type.isWork());

    type = TimeIntervalType.ABSENT;
    assertEquals(false, type.isWork());
  }

  @Test
  void testIsNotWork() {
    TimeIntervalType type = TimeIntervalType.WORK;
    assertEquals(false, type.isNotWork());

    type = TimeIntervalType.ABSENT;
    assertEquals(true, type.isNotWork());
  }

  @Test
  void testIsAbsent() {
    TimeIntervalType type = TimeIntervalType.ABSENT;
    assertEquals(true, type.isAbsent());

    type = TimeIntervalType.WORK;
    assertEquals(false, type.isAbsent());
  }

  @Test
  void testIsNotAbsent() {
    TimeIntervalType type = TimeIntervalType.ABSENT;
    assertEquals(false, type.isNotAbsent());

    type = TimeIntervalType.WORK;
    assertEquals(true, type.isNotAbsent());
  }

  @Test
  void testIsNoTime() {
    TimeIntervalType type = TimeIntervalType.NO_TIME;
    assertEquals(true, type.isNoTime());

    type = TimeIntervalType.WORK;
    assertEquals(false, type.isNoTime());
  }

  @Test
  void testIsNotNoTime() {
    TimeIntervalType type = TimeIntervalType.NO_TIME;
    assertEquals(false, type.isNotNoTime());

    type = TimeIntervalType.ABSENT;
    assertEquals(true, type.isNotNoTime());
  }
}
