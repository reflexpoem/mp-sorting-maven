# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Sunjae Kim
* Samuel A. Rebelsky (starter code)

Acknowledgements

ChatGPT

* _Forthcoming_.

This code may be found at <https://github.com/reflexpoem/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------

Key Features:
Hybrid of Timsort and Insertion Sort:

Small runs are sorted with Insertion Sort for efficiency.
Larger sorted runs are merged using a modified merge step.
Merge Optimization:

The merge step reduces unnecessary data movement.
Adaptive:

Efficient for real-world data patterns, including nearly sorted arrays, reverse-sorted arrays, and random data.
Complexity:
Best Case: O(n)

O(n) (already sorted input).
Average Case: O(n log n)


Worst Case: 
O(nlogn) (heavily optimized via Timsort)

Notes on using Copilot (or other AI)
------------------------------------

I have used chatGPT to create the custom sorting algorithm. 


