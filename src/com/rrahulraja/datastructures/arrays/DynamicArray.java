package com.rrahulraja.datastructures.arrays;

import java.util.Iterator;

/**
 * A generic dynamic array implementation
 */

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
	
	private T[] arr;
	private int capacity;
	private int length;
	
	public DynamicArray() {
		this(16);
	}
	
	public DynamicArray(int capacity) {
		this.capacity = capacity;
	}
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public T get(int index) {
		if(index >= length || index < 0)
			throw new IndexOutOfBoundsException();
		
		return arr[index];
	}
	
	public void set(int index, T element) {
		if(index >= length || index < 0)
			throw new IndexOutOfBoundsException();
		
		arr[index] = element;
	}
	
	public void clear() {
		for(int i = 0; i < length; i++) {
			arr[i] = null;
		}
		
		length = 0;
	}
	
	public void add(T element) {
		if(length+1 >= capacity) {
			if(capacity == 0) {
				capacity = 1;
			} else {
				capacity = capacity * 2;
			}
			
			T[] newArray = (T[]) new Object[capacity];
			
			for(int i = 0; i < length; i++) {
				newArray[i] = arr[i];
			}
			
			arr = newArray;
		}
		
		arr[length++] = element;
	}
	
	public T removeAt(int index) {
		if(index >= length || index < 0)
			throw new IndexOutOfBoundsException();
		
		T data = arr[index];
		
		T[] newArray = (T[]) new Object[length - 1];
		for(int i = 0, j = 0; i < length; i++, j++) {
			if(i == index)
				j--;
			else 
				newArray[i] = arr[j];
		}
		
		arr = newArray;
		capacity = length - 1;
		
		return data;
	}
	
	public boolean remove(T element) {
		int index = indexOf(element);
		if(index == -1)
			return false;
		
		removeAt(index);
		return true;
	}
	
	
	public int indexOf(T element) {
		for(int i = 0; i < length; i++) {
			if(element == null) {
				if(arr[i] == null) {
					return i;
				}
			} else {
				if(arr[i].equals(element)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public boolean contains(T element) {
		return indexOf(element) != -1;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < length;
			}

			@Override
			public T next() {
				return arr[index];
			}			
		};
	}

	@Override
	public String toString() {
		if(length == 0)
			return "[]";
		
		
		StringBuilder sb = new StringBuilder(length);
		sb.append("[");
		for(int i = 0; i < length - 1; i++) {
			sb.append(arr[i]).append(",");
		}
		
		sb.append(arr[length - 1]).append("]");
		
		return sb.toString();
	}
	
	
	
	
	
//	private T[] arr;
//	private int capacity;
//	private int length;
//	
//	public DynamicArray() {
//		this(16);
//	}
//	
//	public DynamicArray(int capacity) {
//		this.capacity = capacity;
//	}
//	
//	public int size() {
//		return length;
//	}
//	
//	public boolean isEmpty() {
//		return size() == 0;
//	}
//	
//	public T get(int index) {
//		if(index >= length || index < 0)
//			throw new IndexOutOfBoundsException();
//		
//		return arr[index];
//	}
//	
//	public void set(int index, T element) {
//		if(index >= length || index < 0)
//			throw new IndexOutOfBoundsException();
//		
//		arr[index] = element;
//	}
//	
//	public void clear() {
//		for(int i = 0; i < length; i++) {
//			arr[i] = null;
//		}
//		
//		length = 0;
//	}
//	
//	public void add(T element) {
//		if(length + 1 >= capacity) {
//			if(capacity == 0)
//				capacity = 1;
//			else 
//				capacity = capacity * 2;
//			
//			T[] newArr = (T[]) new Object[capacity];
//			
//			for(int i = 0; i < length; i++) {
//				newArr[i] = arr[i];
//			}
//			
//			arr = newArr;
//		}
//		
//		arr[length++] = element;
//	}
//	
//	public T removeAt(int index) {
//		if(index >= length || index < 0)
//			throw new IndexOutOfBoundsException();
//		
//		T data = arr[index];
//		
//		T[] newArray = (T[]) new Object[length - 1];
//		for(int i = 0, j = 0; i < length; i++, j++) {
//			if(i == index)
//				j--; // skip over index by fixing j temporarily
//			else 
//				newArray[j] = arr[i];
//		}
//		
//		arr = newArray;
//		capacity = --length;
//		
//		return data;
//	}
//	
//	public boolean remove(Object obj) {
//		int index = indexOf(obj);
//		if(index == -1)
//			return false;
//		
//		removeAt(index);
//		return true;
//	}
//	
//	public int indexOf(Object obj) {
//		for(int i = 0; i < length; i++) {
//			if(obj == null) {
//				if(arr[i] == null)
//					return i;
//			} else {
//				if(obj.equals(arr[i])) {
//					return i;
//				}
//			}
//		}
//		
//		return -1;
//	}
//	
//	public boolean contains(Object obj) {
//		return indexOf(obj) == -1;
//	}
//	
//	@Override
//	public Iterator<T> iterator() {
//		return new java.util.Iterator<T>() {
//			int index = 0;
//
//			@Override
//			public boolean hasNext() {
//				return index < length;
//			}
//
//			@Override
//			public T next() {
//				return arr[index++];
//			}			
//		};
//	}
//
//	@Override
//	public String toString() {
//		if(length == 0)
//			return "[]";
//		else {
//			StringBuilder sb = new StringBuilder(length);
//			sb.append("[");
//			for(int i = 0; i < length - 1; i++) {
//				sb.append(arr[i]).append(",");
//			}
//			
//			return sb.append(arr[length - 1] + "]").toString();
//		}		
//	}
}
