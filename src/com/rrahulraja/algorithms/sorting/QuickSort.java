package com.rrahulraja.algorithms.sorting;

/**
 * QuickSort implementation using Hoare partitioning
 * https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
 * 
 * @author Rrahul
 *
 */
public class QuickSort implements InplaceSort {

	@Override
	public void sort(int[] values) {
		QuickSort.quickSort(values);
	}

	public static void quickSort(int[] array) {
		if (array == null)
			return;

		quickSort(array, 0, array.length - 1);
	}

	// Sort intervals [lo,hi] inplace recursively
	public static void quickSort(int[] array, int lo, int hi) {
		if (lo < hi) {
			int splitPoint = partition(array, lo, hi);
			quickSort(array, lo, splitPoint);
			quickSort(array, splitPoint + 1, hi);
		}
	}

	// Performs Hoare partition algorithm for quick sort
	private static int partition(int[] array, int lo, int hi) {
		int pivot = array[lo];
		int i = lo, j = hi + 1;

		while (true) {
			do {
				i++;
			} while (array[i] < pivot);

			do {
				j--;
			} while (array[j] > pivot);

			if (i < j)
				swap(array, i, j);
			else
				return j;
		}
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		InplaceSort sorter = new QuickSort();
		int[] array = { 10, 4, 6, 4, 8, -13, 2, 3 };
		sorter.sort(array);
		// Prints:
		// [-13, 2, 3, 4, 4, 6, 8, 10]
		System.out.println(java.util.Arrays.toString(array));
	}

}
