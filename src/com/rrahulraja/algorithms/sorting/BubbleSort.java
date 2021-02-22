package com.rrahulraja.algorithms.sorting;

import java.util.Arrays;

public class BubbleSort implements InplaceSort {

	@Override
	public void sort(int[] values) {
		BubbleSort.bubbleSort(values);
	}
	
	// Sort the array using bubble sort. The idea behind
	// bubble sort is to look for adjacent indexes which
	// are out of place and interchange their elements
	// until the entire array is sorted.
	public static void bubbleSort(int[] array) {
		if(array == null)
			return;
		
		boolean sorted;
		
		do {
			sorted = true;
			for(int i = 1; i < array.length; i++) {
				if(array[i] < array[i-1]) {
					swap(array, i - 1, i);
					sorted = false;
				}
			}
		} while(!sorted);
		
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		InplaceSort sorter = new BubbleSort();
		int[] array = {10, 4, 6, 8, 13, 2, 3};
		sorter.sort(array);
		
		System.out.println(Arrays.toString(array));
	}

	

}
