package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * A custom sorting algorithm based on Timsort with optimizations for small arrays.
 * @author Sunjae Kim
 * @author Samuel A. Rebelsky
 * @param <T> The type of elements to be sorted.
 */
public class KimSunjaeSort<T> implements Sorter<T> {

  /** The comparator to define the sorting order. */
  private final Comparator<? super T> order;

  /**
   * Constructor to create a sorter with a given comparator.
   *
   * @param comparator The comparator that determines the order of sorting.
   */
  public KimSunjaeSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // end of constructor

  /**
   * Sorts an array using a hybrid Timsort-based algorithm.
   *
   * @param values The array to be sorted.
   * @throws NullPointerException if the array or comparator is null.
   * @post The input array is sorted in ascending order as defined by the comparator.
   */
  @Override
  public void sort(T[] values) {
    if (values == null || values.length <= 1) {
      return; // Already sorted or null array
    } // end of if statement
    timsort(values, 0, values.length - 1);
  } // end of sort method

  /**
   * Implements a Timsort-based hybrid algorithm.
   *
   * @param array The array to be sorted.
   * @param low The starting index of the range to sort.
   * @param high The ending index of the range to sort.
   */
  private void timsort(T[] array, int low, int high) {
    final int runSize = 32;

    // Step 1: Sort small runs with Insertion Sort
    for (int i = low; i <= high; i += runSize) {
      int end = Math.min(i + runSize - 1, high);
      insertionSort(array, i, end);
    } // end of first for loop

    // Step 2: Merge sorted runs
    for (int size = runSize; size < high - low + 1; size *= 2) {
      for (int left = low; left <= high - size; left += 2 * size) {
        int mid = left + size - 1;
        int right = Math.min(left + 2 * size - 1, high);
        merge(array, left, mid, right);
      } // end of second for loop
    } // end of first for loop
  } // end of timsort method

  /**
   * Sorts a small range of the array using Insertion Sort.
   *
   * @param array The array to sort.
   * @param low The starting index of the range to sort.
   * @param high The ending index of the range to sort.
   */
  private void insertionSort(T[] array, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      T key = array[i];
      int j = i - 1;
      while (j >= low && order.compare(array[j], key) > 0) {
        array[j + 1] = array[j];
        j--;
      } // end of while loop
      array[j + 1] = key;
    } // end of for loop
  } // end of insertionSort method

  /**
   * Merges two sorted subarrays into a single sorted array.
   *
   * @param array The array containing the subarrays.
   * @param left The starting index of the first subarray.
   * @param mid The ending index of the first subarray.
   * @param right The ending index of the second subarray.
   */
  private void merge(T[] array, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    @SuppressWarnings("unchecked")
    T[] leftArray = (T[]) new Object[n1];
    @SuppressWarnings("unchecked")
    T[] rightArray = (T[]) new Object[n2];

    System.arraycopy(array, left, leftArray, 0, n1);
    System.arraycopy(array, mid + 1, rightArray, 0, n2);

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
      if (order.compare(leftArray[i], rightArray[j]) <= 0) {
        array[k] = leftArray[i];
        i++;
      } else {
        array[k] = rightArray[j];
        j++;
      } // end of if-else statement
      k++;
    } // end of while loop

    while (i < n1) {
      array[k] = leftArray[i];
      i++;
      k++;
    } // end of while loop

    while (j < n2) {
      array[k] = rightArray[j];
      j++;
      k++;
    } // end of while loop
  } // end of merge method
} // end of KimSunjaeSort class
