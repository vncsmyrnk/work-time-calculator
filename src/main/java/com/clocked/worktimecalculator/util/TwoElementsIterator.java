package com.clocked.worktimecalculator.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TwoElementsIterator<T> implements Iterator<Pair<T, T>> {

  private final List<T> elements;
  private int currentIndex = 0;

  public TwoElementsIterator(List<T> elements) {
    this.elements = elements;
  }

  @Override
  public boolean hasNext() {
    return currentIndex + 1 < elements.size();
  }

  @Override
  public Pair<T, T> next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    T first = elements.get(currentIndex);
    T second = elements.get(currentIndex + 1);

    currentIndex++;

    return new Pair<>(first, second);
  }
}
