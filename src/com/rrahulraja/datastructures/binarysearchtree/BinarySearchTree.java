package com.rrahulraja.datastructures.binarysearchtree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This file contains an implementation of Binary Search Tree (BST). Any
 * comparable data is allowed within this tree (numbers, strings, comparable
 * Objects, etc...). Supported operations include adding, removing, height and
 * containment checks. Furthermore, multiple tree traversal iterators are
 * provided including : 1) Preorder Traversal 2) Inorder traversal 3) Postorder
 * traversal 4) Levelorder traversal
 * 
 * @author Rrahul
 *
 */

public class BinarySearchTree<T extends Comparable<T>> {

	// Tracks the number of nodes in this BST
	private int nodeCount = 0;

	// This BST is a rooted tree so we maintain a handle on the root node
	private Node root = null;

	// Internal node containing node references
	// and the actual node data
	private class Node {
		T data;
		Node left, right;

		public Node(Node left, Node right, T data) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	// Check if this binary tree is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	// Get the number of nodes in this binary tree
	public int size() {
		return nodeCount;
	}

	// Add an element to this binary tree. Returns true
	// if we successfully perform an insertion.
	public boolean add(T elem) {
		// Check if the value already exists in this
		// binary tree, if it does ignore adding it
		if (contains(elem)) {
			return false;

			// Otherwise add this element to the binary tree
		} else {
			root = add(root, elem);
			nodeCount++;
			return true;
		}
	}

	// Private method to recursively add a value in binary tree
	private Node add(Node node, T elem) {
		// Base case: found a leaf node
		if (node == null) {
			node = new Node(null, null, elem);
		} else {
			// Pick a subtree to insert element
			if (elem.compareTo(node.data) < 0) {
				node.left = add(node.left, elem);
			} else {
				node.right = add(node.right, elem);
			}
		}

		return node;
	}

	// Remove a value from this binary tree if it exists, O(n)
	public boolean remove(T elem) {
		// Make sure the node we want to remove
		// actually exists before we remove it
		if (contains(elem)) {
			root = remove(root, elem);
			nodeCount--;
			return true;
		}

		return false;
	}

	private Node remove(Node node, T elem) {
		if (node == null)
			return null;

		int cmp = elem.compareTo(node.data);

		// Dig into the left subtree because the value we're
		// looking for is smaller than current value
		if (cmp < 0)
			return remove(node.left, elem);
		// Dig into the right subtree because the value we're
		// looking for is greater than current value
		else if (cmp > 0)
			return remove(node.right, elem);
		// Found the node we wish to remove
		else {
			// This is the case with only a right subtree or
			// no subtree at all. In this situation just swap
			// the node we wish to remove with its right child
			if (node.left == null) {
				return node.right;

				// This is the case with only a left subtree or
				// no subtree at all. In this situation just swap
				// the node we wish to remove with its left child
			} else if (node.right == null) {
				return node.left;

				// When removing a node from binary search tree with two links the
				// successor of the node being removed can either be the largest
				// value in the left subtree or the smallest value in the right
				// subtree. In the implementation I have decided to find the
				// smallest value in the right subtree which can be founded by
				// traversing as far left as possible in the right subtree
			} else {
				// Find the left most node in the right subtree
				Node tmp = findMin(node.right);

				// swap the data
				node.data = tmp.data;

				// Go into the right subtree and remove the leftmost node we
				// found and swapped data with. This prevents us from having
				// two nodes in our tree with same value.
				node.right = remove(node.right, tmp.data);

				// If instead we wanted to find the largest node in the left
				// subtree as opposed to smallest node in the right subtree
				// here is what we would do:
				// Node tmp = findMax(node.left);
				// node.data = tmp.data;
				// node.left = remove(node.left, tmp.data);
			}

		}

		return node;
	}

	// Helper method to find the leftmost node (which has the smallest value)
	private Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}

		return node;
	}

	// Helper method to find the rightmost node (which has the largest value)
	private Node findMax(Node node) {
		while (node.right != null) {
			node = node.right;
		}

		return node;
	}

	// returns true if element exists in the tree
	public boolean contains(T elem) {
		return contains(root, elem);
	}

	// Private recursive method to find an element in the tree
	private boolean contains(Node node, T elem) {
		// Base case: reached bottom, value not found
		if (node == null)
			return false;

		int cmp = elem.compareTo(node.data);

		// Dig into the left subtree because the value we're
		// looking for is smaller than current value
		if (cmp < 0)
			return contains(node.left, elem);
		// Dig into the right subtree because the value we're
		// looking for is greater than current value
		else if (cmp > 0)
			return contains(node.right, elem);
		// We found the value we were looking for
		else
			return true;
	}

	// Computes the height of tree, O(n)
	public int height() {
		return height(root);
	}

	// Recursive helper method to compute the height of the tree
	private int height(Node node) {
		if (node == null)
			return 0;
		
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);

		return Math.max(leftHeight, rightHeight) + 1;
	}

	// This method returns an Iterator for given TreeTraversalOrder
	// The ways in which you can traverse the tree are in four different ways.
	// preorder, inorder, postorder and levelorder
	public Iterator<T> traverse(TreeTraversalOrder order) {
		switch (order) {
		case PRE_ORDER:
			return preOrderTraversal();
		case IN_ORDER:
			return inOrderTraversal();
		case POST_ORDER:
			return postOrderTraversal();
		case LEVEL_ORDER:
			return levelOrderTraversal();
		default:
			return null;

		}
	}

	// Returns an iterator to traverse the tree in preorder
	private Iterator<T> preOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Stack<Node> stack = new Stack<>();
		stack.push(root);

		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();

				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();

				Node node = stack.pop();

				if (node.right != null)
					stack.push(node.right);

				if (node.left != null) {
					stack.push(node.left);
				}

				return node.data;
			}
		};
	}

	// Returns as iterator to traverse the tree in order
	private Iterator<T> inOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Stack<Node> stack = new Stack<>();
		stack.push(root);

		return new Iterator<T>() {
			Node trav = root;

			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();

				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();

				// Dig left
				while (trav != null && trav.left != null) {
					stack.push(trav.left);
					trav = trav.left;
				}

				Node node = stack.pop();

				// Try moving down right once
				if (node.right != null) {
					stack.push(node.right);
					trav = node.right;
				}

				return node.data;
			}
		};
	}

	// Returns as iterator to traverse the tree in post order
	private Iterator<T> postOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Stack<Node> stack1 = new Stack<>();
		final Stack<Node> stack2 = new Stack<>();
		stack1.push(root);

		while (!stack1.isEmpty()) {
			Node node = stack1.pop();

			if (node != null) {
				stack2.push(node);

				if (node.left != null)
					stack1.push(node.left);

				if (node.right != null)
					stack1.push(node.right);
			}
		}

		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();

				return root != null && !stack2.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();

				return stack2.pop().data;
			}

		};
	}

	// Returns as iterator to traverse the tree in level order
	private Iterator<T> levelOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Queue<Node> queue = new LinkedList<>();
		queue.offer(root);

		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				if(expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();
				
				return root != null && !queue.isEmpty();
			}

			@Override
			public T next() {
				if(expectedNodeCount != nodeCount)
					throw new ConcurrentModificationException();
				
				Node node = queue.poll();
				if(node.left != null)
					queue.offer(node.left);
				
				if(node.right != null)
					queue.offer(node.right);
				
				return node.data;
			}
		};
	}

}
