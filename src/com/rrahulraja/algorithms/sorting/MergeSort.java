package com.rrahulraja.algorithms.sorting;

import java.util.Arrays;

/**
 * Mergesort implements InplaceSort for ease of testings, but in reality it is
 * not really a good fit for an inplace sorting algorithm.
 * 
 * @author Rrahul
 *
 */
public class MergeSort implements InplaceSort {

	@Override
	public void sort(int[] values) {
		int[] sortedValues = MergeSort.mergeSort(values);
		for (int i = 0; i < values.length; i++) {
			values[i] = sortedValues[i];
		}
	}

	public static int[] mergeSort(int[] array) {
		// Base case when a single element (which is already sorted)
		int n = array.length;
		if (n <= 1)
			return array;

		// Split array into two parts and recursively sort them
		int[] left = mergeSort(Arrays.copyOfRange(array, 0, n/2));
		int[] right = mergeSort(Arrays.copyOfRange(array, n/2, n));
		
		// Combine the two arrays into one larger array
		return merge(left, right);
	}
	
	// Merge two sorted arrays into a larger sorted array
	private static int[] merge(int[] array1, int[] array2) {
		int sizeOfArray1 = array1.length, sizeOfArray2 = array2.length;
		
		int sizeOfArray = sizeOfArray1 + sizeOfArray2, indexOfArray1 = 0, indexOfArray2 = 0;
		int[] array = new int[sizeOfArray];
		
		for(int i = 0; i < sizeOfArray; i++) {
			if(indexOfArray1 == sizeOfArray1) {
				array[i] = array2[indexOfArray2++];
			} else if(indexOfArray2 == sizeOfArray2) {
				array[i] = array1[indexOfArray1++];
			} else {
				if(array1[indexOfArray1] < array2[indexOfArray2]) {
					array[i] = array1[indexOfArray1++];
				} else {
					array[i] = array2[indexOfArray2++];
				}
			}
		}
		
		return array;
	}

	public static void main(String[] args) {
		int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
	    array = MergeSort.mergeSort(array);
	    // Prints:
	    // [-13, 2, 3, 4, 4, 6, 8, 10]
	    System.out.println(java.util.Arrays.toString(array));

	}

}
