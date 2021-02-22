package com.rrahulraja.algorithms.sorting;

import java.util.Arrays;

/**
 * Selection sort implementation
 * @author Rrahul
 *
 */
public class SelectionSort implements InplaceSort {

	@Override
	public void sort(int[] values) {
		SelectionSort.selectionSort(values);
	}
	
	public static void selectionSort(int[] array) {
		if(array == null)
			return;
		
		final int N = array.length;
		
		for(int i = 0; i < N; i++) {
			// Find the index beyond i with a lower value than i
			int swapIndex = i;
			
			for(int j = i+1; j < N; j++) {
				if(array[j] < array[swapIndex]) {
					swapIndex = j;
				}
			}
			
			swap(array, i, swapIndex);
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		InplaceSort sorter = new SelectionSort();
		int[] array = {10, 4, 6, 8, 13, 2, 3};
		sorter.sort(array);
		
		System.out.println(Arrays.toString(array));
	}

}
