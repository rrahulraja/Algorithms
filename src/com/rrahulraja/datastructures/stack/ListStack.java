package com.rrahulraja.datastructures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<T> implements Iterable<T>, Stack<T>{
	private LinkedList<T> list = new LinkedList<>();
	
	// Create an empty stack
	public ListStack() {}
	
	// Create a stack with initial element
	public ListStack(T firstElem) {
		push(firstElem);
	}

	// Return the number of elements in stack
	@Override
	public int size() {
		return list.size();
	}

	// Check if the stack is empty
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	// Push an element on the stack
	@Override
	public void push(T elem) {
		list.addLast(elem);		
	}

	// Pop an element off the stack
	// Throws an error if stack is empty
	@Override
	public T pop() {
		if(isEmpty())
			throw new EmptyStackException();
		
		return list.removeLast(); 
	}

	// Peek the top of the stack without removing an element
	// Throws an exception if the stack is empty
	@Override
	public T peek() {
		if(isEmpty())
			throw new EmptyStackException();
		
		return list.peekLast();		
	}
	
	// Searches for the element starting from top of the stack
	// Returns -1 if the element is not present in the stack
	public int search(T elem) {
		return list.lastIndexOf(elem);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
}
