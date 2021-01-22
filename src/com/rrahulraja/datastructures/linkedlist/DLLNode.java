package com.rrahulraja.datastructures.linkedlist;

public class DLLNode<T> {
	public T data;
	public DLLNode<T> prev;
	public DLLNode<T> next;
	
	public DLLNode(T data, DLLNode<T> prev, DLLNode<T> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
}
