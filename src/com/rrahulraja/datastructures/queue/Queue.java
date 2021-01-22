package com.rrahulraja.datastructures.queue;

/**
 * 
 * @author Rrahul
 *
 * @param <T> the type of element held in the queue
 */
public interface Queue<T> {
	
	public void offer(T elem);
	
	public T poll();
	
	public T peek();
	
	public int size();
	
	public boolean isEmpty();

}
