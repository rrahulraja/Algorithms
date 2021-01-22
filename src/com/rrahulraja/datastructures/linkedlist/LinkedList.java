package com.rrahulraja.datastructures.linkedlist;

public class LinkedList {
	private int size;
	private Node head;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * insert element at the head of linked list
	 */	
	public void addHead(int value) {
		head = new Node(value, head);
		size++;
	}
	
	/**
	 * insert element at the end of linked list
	 */	
	public void addTail(int value) {
		Node newNode = new Node(value, null);
		Node current = head;
		
		if(head == null)
			head = newNode;
		
		while(current.next != null) {
			current = current.next;
		}
		
		current.next = newNode;
		
		size++;
	}
	/**
	 * return the value present at passed index
	 * @param index
	 * @return
	 */
	public int valueAt(int index) {
		Node current = head;
		int counter = 0;
		
		while(current.next != null) {
			if(counter == index) 
				return current.value;
			
			counter++;
			current = current.next;
		}
		
		return -1;
	}
	
	/**
	 * delete the first item of list
	 * @return
	 */
	public int deleteFromFront() {
		if(isEmpty()) {
			throw new IllegalStateException("EmptyListException");
		}
		
		int value = head.value;
		head = head.next;
		size--;
		return value;
	}
	
	/**
	 * delete the last item of list
	 * @return
	 */
	public int deleteFromLast() {
		Node currentNode = head;
		Node previousNode = null;
		
		while(currentNode.next != null) {
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		
		int value = currentNode.value;
		previousNode.next = null;
		
		size--;
		return value;
	}
	
	/**
	 * get value of the front item of list
	 * @return
	 */
	public int front() {
		return head.value;
	}
	
	/**
	 * return the last value of the list
	 * @return
	 */
	public int back() {
		Node currentNode = head;
		while(currentNode.next != null) {
			currentNode = currentNode.next;
		}
		
		return currentNode.value;
	}
	
	public void insertItemAtIndex(int index, int value) {
		if(isEmpty() || index < 0 || index > size() ) {
			throw new IllegalStateException();
		}
		
		
		int counter = 0;
		Node currentNode = head;
		Node previousNode = null;
		
		while(counter < index) {
			previousNode = currentNode;
			currentNode = currentNode.next;
			counter++;
		}
		
		Node newNode = new Node(value, null);
		newNode.next = currentNode;
		previousNode.next = newNode;
		size++;
	}
	
	public void removeItemAtIndex(int index) {
		if(isEmpty() || index < 0 || index > size() ) {
			throw new IllegalStateException();
		}
		
		int counter = 0;
		Node currentNode = head;
		Node previousNode = null;
		
		while(counter < index) {
			previousNode = currentNode;
			currentNode = currentNode.next;
			counter++;
		}
		
		previousNode.next = currentNode.next;	
		size--;
	}
	
	public int nthValueFromEnd(int n) {
		Node fast = head;
		Node slow = head;
		int counter = 0;
		
		while(counter < n) {
			fast = fast.next;
			counter = counter++;
		}
		
		while(fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow.value;
	}
	
	public void reverse() {
		Node current = head;
		Node previous = null;
		Node next = null;
		
		while(current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		
		head = previous;
	}
	

}
