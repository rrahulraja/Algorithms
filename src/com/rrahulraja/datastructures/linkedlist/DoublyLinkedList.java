package com.rrahulraja.datastructures.linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
	private int size = 0;
	private DLLNode<T> head;
	private DLLNode<T> tail;
	
	// Empty the list O(n)
	public void clear() {
		DLLNode<T> trav = head;
		
		while(trav != null) {
			DLLNode<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		
		head = tail = trav = null;
		size = 0;
	}
	
	// Return the size of linked list
	public int size() {
		return size;
	}
	
	// Is linked list empty?
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Add an element to the tail of the linked list O(1)
	public void add(T element) {
		addLast(element);
	}
	
	// Add a node to the tail of the linked list O(1)
	public void addLast(T elem) {
		if(isEmpty()) {
			head = tail = new DLLNode<T>(elem, null, null);
		} else {
			tail.next = new DLLNode<T>(elem, null, null);
			tail = tail.next;
		}
		
		size++;
	}
	
	// Add an element to the beginning of the linked list O(1)	
	public void addFirst(T elem) {
		if(isEmpty()) {
			head = tail = new DLLNode<T>(elem, null, null);
		} else {
			head.prev = new DLLNode<T>(elem, null, null);
			head = head.prev;
		}
		
		size++;
	}
	
	// Add an element to a specified index
	public void addAt(int index, T data) {
		if(index < 0) 
			throw new IllegalArgumentException("Illegal index");
		
		if(index == 0) {
			addFirst(data);
			return;
		}
		
		if(index == size) {
			addLast(data);
			return;
		}
		
		DLLNode<T> temp = head;
		for(int i = 0; i < index - 1; i++) {
			temp = temp.next;
		}
		
		DLLNode<T> newNode = new DLLNode<T>(data, null, null);
		temp.next.prev = newNode;
		temp.next = newNode;

		size++;			
	}
	
	// Check the value of the first node if it exists, O(1)
	public T peekFirst() {
		if(isEmpty())
			throw new RuntimeException("Empty list");
		
		return head.data;
	}
	
	// Check the value of the last node if it exists, O(1)
	public T peekLast() {
		if(isEmpty()) 
			throw new RuntimeException("Empty list");
		
		return tail.data;
	}
	
	// Remove the first value at the head of the linked list, O(1)
	public T removeFirst() {
		if(isEmpty()) 
			throw new RuntimeException("Empty list");
		
		// Extract the data at the head and move
		// the head pointer forwards one node
		T data = head.data;
		head = head.next;
		--size;
		
		// If the list is empty set the tail to null;
		if(isEmpty())
			tail = null;
		// Do a memory clean of the previous node		
		else
			head.prev = null;
		
		// Return the data that was at the first node we just removed
		
		return data;
	}
	
	// Remove the last value at the tail of linked list, O(1)
	public T removeLast() {
		if(isEmpty()) 
			throw new RuntimeException("Empty list");
		
		// Extract the data at the tail and move
		// the tail pointer backwards one node
		T data = tail.data;
		tail = tail.prev;
		--size;
		
		// If the list is empty set the head to null;
		if(isEmpty())
			head = null;
		// Do a memory clean of the node that was just removed
		else
			tail.next = null;
		
		// Return the data that was at the last node we just removed
		return data;			
	}
	
	// Remove the arbitrary node from the list, O(1)
	public T remove(DLLNode<T> node) {
		// If the node to remove is somewhere either at the
		// head or tail handle those independently
		if(node.prev == null)
			return removeFirst();
		
		if(node.next == null)
			return removeLast();
		
		// Make the pointers of the adjacent nodes skip over 'node'
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		// Temporarily store the data we want to return
		T data = node.data;
		
		// Memory cleanup
		node.data = null;
		node = node.prev = node.next = null;
		
		--size;
		
		// Return the data in the node we just removed
		return data;
	}
	
	// Remove a node at particular index, O(n)
	public T removeAt(int index) {
		// Make sure the index provided is valid
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("Invalid index");
		}
		
		int i;
		DLLNode<T> trav;
		
		// Search from the front of the list
		if(index < size / 2) {
			for(i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		} else {
			for(i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		
		return remove(trav);		
	}
	
	// Remove a particular value in the linked list, O(n)
	public boolean remove(Object obj) {
		DLLNode<T> trav = head;
		
		// Support searching for null
		if(obj == null) {
			for(trav = head; trav != null; trav = trav.next) {
				if(trav.data == null) {
					remove(trav);
					return true;
				}
			}
		} else {
			for(trav = head; trav != null; trav = trav.next) {
				if(obj.equals(trav.data)) {
					remove(trav);
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Find the index of a particular value in the linked list, O(n)
	public int indexOf(Object obj) {
		int index = 0;
		DLLNode<T> trav = head;
		
		//Support searching for null
		if(obj == null) {
			for(; trav != null; trav = trav.next, index++) {
				if(trav.data == null) {
					return index;
				}					
			}
		} else {
			for(; trav != null; trav = trav.next, index++) {
				if(obj.equals(trav.data)) {
					return index;
				}
			}
		}
		
		return -1;
	}
	
	// Check is a value is contained within the linked list
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private DLLNode<T> trav = head;

			@Override
			public boolean hasNext() {
				return trav.next != null;
			}

			@Override
			public T next() {
				T data = trav.next.data;
				trav = trav.next;
				
				return data;
			}
			
		};				
	}
	

}
