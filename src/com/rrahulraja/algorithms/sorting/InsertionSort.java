package com.rrahulraja.algorithms.sorting;

import java.util.Arrays;

/**
 * Insertion Sort implementation
 * @author Rrahul
 *
 */
public class InsertionSort implements InplaceSort {

	@Override
	public void sort(int[] values) {
		InsertionSort.insertionSort(values);
	}
	
	// Sort the given array using insertion sort. The idea behind
	// insertion sort is that at the array is already sorted from
	// [0, i] and you want to add the element at position i+1, so
	// you 'insert' it at the appropriate location.
	public static void insertionSort(int[] array) {
		if(array == null)
			return;
		
		for(int i = 0; i < array.length; i++) {
			for(int j = i; j > 0 && array[j] < array[j-1]; j--) {
				swap(array, j - 1, j);
			}
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		InplaceSort sorter = new InsertionSort();
		int[] array = {10, 4, 6, 8, 13, 2, 3};
		sorter.sort(array);
		
		System.out.println(Arrays.toString(array));
	}

	

}
