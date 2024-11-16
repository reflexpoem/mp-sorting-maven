package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * A class that sorts an array using merge sort.
 * @author Sunjae Kim
 * @author Samuel A. Rebelsky
 * @param <T> The types of values that are sorted.
 */
public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The comparator to define the order of sorting. */
  private final Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor to create a sorter using a particular comparator.
   *
   * @param comparator The comparator that determines the sorting order.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // end of constructor

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sorts an array in place using merge sort.
   *
   * @param values The array to sort.
   * @post The array is sorted according to the specified comparator.
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1],
   *       values[i]) &lt;= 0
   * @throws NullPointerException if the array or comparator is null.
   */
  @Override
  public void sort(T[] values) {
    if (values == null || values.length < 2) {
      return; // Already sorted or null
    } // end of if statement

    int midIndex = values.length / 2;
    @SuppressWarnings("unchecked")
    T[] leftHalf = (T[]) new Object[midIndex];
    @SuppressWarnings("unchecked")
    T[] rightHalf = (T[]) new Object[values.length - midIndex];

    // Split the array into left and right halves
    for (int i = 0; i < midIndex; i++) {
      leftHalf[i] = values[i];
    } // end of for loop

    for (int i = midIndex; i < values.length; i++) {
      rightHalf[i - midIndex] = values[i];
    } // end of for loop

    // Recursively sort the left and right halves
    sort(leftHalf);
    sort(rightHalf);

    // Merge the sorted halves back together
    merge(values, leftHalf, rightHalf);
  } // end of sort method

  /**
   * Merges two sorted subarrays into the original array.
   *
   * @param values    The array into which the sorted elements are merged.
   * @param leftHalf  The sorted left subarray.
   * @param rightHalf The sorted right subarray.
   * @throws NullPointerException if any of the arrays are null.
   */
  private void merge(T[] values, T[] leftHalf, T[] rightHalf) {
    int leftSize = leftHalf.length;
    int rightSize = rightHalf.length;

    int i = 0; // Index for leftHalf
    int j = 0; // Index for rightHalf
    int k = 0; // Index for merged array

    // Merge elements from leftHalf and rightHalf in sorted order
    while (i < leftSize && j < rightSize) {
      if (order.compare(leftHalf[i], rightHalf[j]) <= 0) {
        values[k++] = leftHalf[i++];
      } else {
        values[k++] = rightHalf[j++];
      } // end of if-else
    } // end of while loop

    // Copy any remaining elements from leftHalf
    while (i < leftSize) {
      values[k++] = leftHalf[i++];
    } // end of while loop

    // Copy any remaining elements from rightHalf
    while (j < rightSize) {
      values[k++] = rightHalf[j++];
    } // end of while loop
  } // end of merge method
} // end of MergeSorter class
