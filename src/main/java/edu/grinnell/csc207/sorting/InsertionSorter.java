package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T> The types of values that are sorted.
 * @author Sunjae Kim
 * @author Samuel A. Rebelsky
 */
public class InsertionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The way in which elements are ordered. */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 1; i < values.length; i++) {
      T key = values[i];
      int j = i - 1;

      // Move elements of values[0..i-1] that are greater than key
      // to one position ahead of their current position
      while (j >= 0 && order.compare(values[j], key) > 0) {
        values[j + 1] = values[j];
        j = j - 1;
      } // end of while loop
      values[j + 1] = key;
    } // end of for loop
  } // sort(T[])
} // class InsertionSorter
