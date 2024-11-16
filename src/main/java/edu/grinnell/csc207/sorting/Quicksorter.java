package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 * @author Sunjae Kim
 * @author Samuel A. Rebelsky
 */
public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    quicksort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * The recursive quicksort method.
   *
   * @param array The array to sort.
   * @param low The starting index of the portion to sort.
   * @param high The ending index of the portion to sort.
   */
  private void quicksort(T[] array, int low, int high) {
    if (low < high) {
      // Partition the array and get the pivot index
      int pivotIndex = partition(array, low, high);

      // Recursively sort elements before and after partition
      quicksort(array, low, pivotIndex - 1);
      quicksort(array, pivotIndex + 1, high);
    } // end of if statement
  } // end of quicksort method

  /**
   * Partition the array such that elements smaller than the pivot are on the left, and elements
   * greater than or equal to the pivot are on the right.
   *
   * @param array The array to partition.
   * @param low The starting index of the portion to partition.
   * @param high The ending index of the portion to partition.
   * @return The index of the pivot after partitioning.
   */
  private int partition(T[] array, int low, int high) {
    T pivot = array[high]; // Choose the last element as pivot
    int i = low - 1; // Index of smaller element

    for (int j = low; j < high; j++) {
      // If the current element is smaller than the pivot
      if (order.compare(array[j], pivot) < 0) {
        i++;
        // Swap array[i] and array[j]
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      } // end of if statment
    } // end of for loop

    // Swap array[i+1] and array[high] (or pivot)
    T temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;

    return i + 1; // Return the index of the pivot
  } // end of partition method
} // class Quicksorter
