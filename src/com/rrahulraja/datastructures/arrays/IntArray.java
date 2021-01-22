package com.rrahulraja.datastructures.arrays;

import java.util.Arrays;
import java.util.Iterator;

public class IntArray implements Iterable<Integer>{
	
	private static final int DEFAULT_CAP = 1 << 3;

	public int[] arr;
	public int len = 0;
	private int capacity;
	
	public IntArray() {
		this(DEFAULT_CAP);
	}
	
	public IntArray(int capacity) {
		if(capacity < 0)
			throw new IllegalArgumentException("Illegal capacity : " + capacity);
		
		this.capacity = capacity;
		arr = new int[capacity];
	}
	
	public IntArray(int[] array) {
		if(array == null)
			throw new IllegalArgumentException("Array cannot be null");
		
		arr = Arrays.copyOf(array, array.length);
		capacity = len = arr.length;
	}
	
	// Returns the size of the array
	public int size() {
		return len;
	}
	
	// Returns true/false on whether the array is empty
	public boolean isEmpty() {
		return len == 0;
	}
	
	// To get/set values without method call overhead you can do
	// array_obj.arr[index] instead, you can gain about 10x the speed
	public int get(int index) {
		return arr[index];
	}
	
	public void set(int index, int elem) {
		arr[index] = elem;
	}
	
	// Add an element to this dynamic array
	public void add(int elem) {
		if(len + 1 > capacity) {
			if(capacity == 0)
				capacity = 1;
			else 
				capacity = capacity * 2;
			
			arr = Arrays.copyOf(arr, capacity); // pads with extra 0/null elements
		}
		
		arr[len++] = elem;
	}
	
	// Removes the element at the specified index in the list
	// If possible, avoid calling this method as it take O(n) time
	// to remove an element (since you have to reconstruct the array!)
	public void removeAt(int index) {
		System.arraycopy(arr, index+1, arr, index, index-1);
		--len;
		--capacity;
	}
	
	// Search and remove element, if found in array
	// If possible, avoid calling this method as it takes O(n) time
	public boolean remove(int elem) {
		for(int i = 0; i < len; i++) {
			if(arr[i] == elem) {
				removeAt(i);
				
				return true;
			}
		}
		
		return false;
	}
	
	// Reverse the contents of array
	public void reverse() {
		for(int i = 0; i < len/2; i++) {
			int temp = arr[i];
			arr[i] = arr[len - i - 1];
			arr[len - i - 1] = temp;
		}
	}
	
	// Perform a binary search on this array to find an element in O(log(n)) time
	// Make sure this array is sorted! Returns a value < 0, if item is not found
	public int binarySearch(int key) {
		int index = Arrays.binarySearch(arr, 0, len, key);
		return index;		
	}
	
	// Sort this array
	public void sort() {
		Arrays.sort(arr, 0, len);
	}
	
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < len;
			}

			@Override
			public Integer next() {
				return arr[index];				
			}
		};
	}
	
	@Override
	public String toString() {
		if(len == 0)
			return "[]";
		
		
		StringBuilder sb = new StringBuilder(len);
		sb.append("[");
		for(int i = 0; i < len - 1; i++) {
			sb.append(arr[i]).append(",");
		}
		
		sb.append(arr[len - 1]).append("]");
		
		return sb.toString();
	}
	
	 // Example usage
	  public static void main(String[] args) {

	    IntArray ar = new IntArray(50);
	    ar.add(3);
	    ar.add(7);
	    ar.add(6);
	    ar.add(-2);

	    ar.sort(); // [-2, 3, 6, 7]

	    // Prints [-2, 3, 6, 7]
	    for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

	    // Prints [-2, 3, 6, 7]
	    System.out.println(ar);
	  }
	

}
