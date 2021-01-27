package com.rrahulraja.datastructures.queue;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedQueue<T> implements Queue<T>, Iterable<T> {
	
	private LinkedList<T> list = new LinkedList<>();
	
	public LinkedQueue() {}
	
	public LinkedQueue(T firstElem) {
		offer(firstElem);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public void offer(T elem) {
		list.addLast(elem);
	}

	@Override
	public T poll() {
		if(isEmpty())
			throw new RuntimeException("Queue is empty");
		
		return list.removeFirst();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public T peek() {
		if(isEmpty())
			throw new RuntimeException("Queue is empty");
		
		return list.peekFirst();
	}
	
}
