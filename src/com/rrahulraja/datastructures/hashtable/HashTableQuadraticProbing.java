package com.rrahulraja.datastructures.hashtable;

public class HashTableQuadraticProbing<K,V> extends HashTableOpenAddressingBase<K, V>{
	
	public HashTableQuadraticProbing() {
		super();
	}
	
	public HashTableQuadraticProbing(int capacity) {
		super(capacity);
	}
	
	public HashTableQuadraticProbing(int capacity, double loadFactor) {
		super(capacity, loadFactor);
	}

	@Override
	protected void setupProbing(K key) {
	}

	@Override
	protected int probe(int x) {
		// Quadratic probing function (x^2 + x) / 2
		return (x * x + x) >> 1;
	}

	@Override
	protected void adjustCapacity() {
		int pow2 = Integer.highestOneBit(capacity);
		if(capacity == pow2)
			return;
		
		increaseCapacity();		
	}

	@Override
	protected void increaseCapacity() {
		capacity = nextPowerOfTwo(capacity);
	}
	
	// Give a number this method finds the next
	// power of two above this value
	private static int nextPowerOfTwo(int n) {
		return Integer.highestOneBit(n) << 1;
	}
}
