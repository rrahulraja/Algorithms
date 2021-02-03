package com.rrahulraja.algorithms.other;

/**
 * Fundamental bit manipulation operations you must know. Time Complexity : O(1)
 * @author Rrahul
 *
 */
public class BitManipulations {
	
	// Set's the position bit to 1
	// Example - 
	// x 		: 00000110 (6)
	// position : 00000101 (5)
	// mask		: 00100000
	// -----
	// x | mask : 00100110
	
	public static int setBit(int x, int position) {
		int mask = 1 << position;
		return x | mask;
	}
	
	// Set's the position bit to zero
	// Example - 
	// x 		: 00000110 (6)
	// position : 00000010 (2)
	// mask		: 00000100
	// ~mask	: 11111011
	// -----
	// x & ~mask : 00000010
	public static int clearBit(int x, int position) {
		int mask = 1 << position;
		return x & ~mask;
	}
	
	// Toggles the i'th bit from 0 -> 1 or 1 -> 0
	// Example - 
	// x 		: 01100110 (6)
	// position : 00000010 (2)
	// mask		: 00000100
	// -----
	// x ^ mask : 01100010
	public static int toggleBit(int x, int position) {
		int mask = 1 << position;
		return x ^ mask;
	}
	
	// Checks if the i'th is set
	public static boolean isSet(int x, int position) {
		int shifted = x >> position;
		return (shifted & 1) != 0;
	}
	
	public static boolean isEven(int num) {
		return (num & 1) == 0; 
	}
	
	public static boolean isPowerOfTwo(int num) {
		return (num & num - 1) == 0;
	}
	
	/**
	 * Count the number of bits that are different between two numbers
	 */
	public static int countDifferentBitsBetweenNumbers(int num1, int num2) {
		int x = num1 ^ num2;
		
		int count = 0;
		
		while(x != 0) {
			x = x & x - 1;
			count++;
		}
		
		return count;
	}
	
	// Returns the number with first n bits set to 1
	public static int setAll(int num) {
		return (1 << num) - 1;
	}
	
}
