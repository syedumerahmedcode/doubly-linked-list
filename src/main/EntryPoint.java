package main;

public class EntryPoint {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> doublyLinkedList=new DoublyLinkedList<Integer>();
		doublyLinkedList.addFirst(1);		
		doublyLinkedList.add(2);
		doublyLinkedList.add(3);
		doublyLinkedList.add(4);
		doublyLinkedList.addLast(5);
		
		System.out.println(doublyLinkedList.toString());

	}

}
