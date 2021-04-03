package main;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

	private final Node<T> NULL_NODE = null;
	private final T NULL_NODE_VALUE = null;
	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	private class Node<T> {
		T data;
		Node<T> prev, next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	// Empty this link list, O(n)
	public void clear() {
		Node<T> trav = head;
		while (trav != NULL_NODE) {
			Node<T> next = trav.next;
			trav.prev = NULL_NODE;
			trav.next = NULL_NODE;
			trav.data = NULL_NODE_VALUE;
			trav = next;
		}
		head = NULL_NODE;
		tail = NULL_NODE;
		trav = NULL_NODE;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	// Add an elemnt to the tail of the linked list, O(1)
	public void add(T element) {
		addLast(element);
	}

	// Add an element to the beginning of the linked list, O(1)
	public void addFirst(T element) {
		// Check if the linked list is empty
		if (isEmpty()) {
			createADoublyLinkedListWhichConsistsOfOnlyOneNode(element);
		} else {
			head.prev = new Node<T>(element, NULL_NODE, head);
			head = head.prev;
		}
		size++;
	}

	private void createADoublyLinkedListWhichConsistsOfOnlyOneNode(T element) {
		head = tail = new Node<T>(element, NULL_NODE, NULL_NODE);
	}

	public void addLast(T element) {
		if (isEmpty()) {
			createADoublyLinkedListWhichConsistsOfOnlyOneNode(element);
		} else {
			tail.next = new Node<T>(element, tail, NULL_NODE);
			tail = tail.next;
		}
		size++;
	}

	public T peekFirst() {
		throwErrorIfListIsEmpty();
		return head.data;

	}

	public T peekLast() {
		throwErrorIfListIsEmpty();
		return tail.data;

	}

	private void throwErrorIfListIsEmpty() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
	}

	public T removeFirst() {
		throwErrorIfListIsEmpty();

		T data = head.data;
		head = head.next;
		--size;

		if (isEmpty()) {
			tail = NULL_NODE;
		} else {
			head.prev = NULL_NODE;
		}
		return data;
	}

	public T removeLast() {
		throwErrorIfListIsEmpty();

		T data = tail.data;
		tail = tail.prev;
		--size;

		if (isEmpty()) {
			head = NULL_NODE;
		} else {
			tail.next = NULL_NODE;
		}
		return data;
	}

	private T remove(Node<T> node) {
		if (node.prev == NULL_NODE) {
			return removeFirst();
		}
		if (node.next == NULL_NODE) {
			return removeLast();
		}
		node.next.prev = node.prev;
		node.prev.next = node.next;

		T data = node.data;
		node.data = NULL_NODE_VALUE;
		node = node.prev = node.next = NULL_NODE;

		--size;
		return data;

	}

	public T removeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		int i;
		Node<T> trav;
		// Search from the front of the list
		if (index < size / 2) {
			for (i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		} else {
			for (i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		return remove(trav);
	}

	public boolean remove(Object object) {
		Node<T> trav = head;

		// Support searching for null
		if (object == NULL_NODE_VALUE) {
			for (trav = head; trav != NULL_NODE; trav = trav.next) {
				if (trav.data == NULL_NODE_VALUE) {
					remove(trav);
					return true;
				}
			}
		} else {
			// Search for non null values
			for (trav = head; trav != NULL_NODE; trav = trav.next) {
				if (object.equals(trav.data)) {
					remove(trav);
					return true;
				}
			}
		}
		return false;
	}

	// Find the index of a particular value in the linked list, O(n)
	public int indexOf(Object object) {
		int index = 0;
		Node<T> trav = head;

		if (object == NULL_NODE_VALUE) {
			for (trav = head; trav != NULL_NODE; trav = trav.next, index++) {
				if (trav.data == NULL_NODE_VALUE) {
					return index;
				}
			}
		} else {
			for (trav = head; trav != NULL_NODE; trav = trav.next, index++) {
				if (object.equals(trav.data)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	public boolean contains(Object object) {
		return indexOf(object)!=-1;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> trav=head;
			@Override
			public boolean hasNext() {
				return trav!=NULL_NODE;
			}
			@Override
			public T next() {
				T data=trav.data;
				trav=trav.next;
				return data;
			}
		};
	}
	
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("[");
		Node<T> trav=head;
		while (trav!=NULL_NODE) {
			builder.append(trav.data+", ");
			trav=trav.next;
		}
		builder.append(" ]");
		return builder.toString();
		
	}

}
