package Calass.copy;

public class LinkedList {
	  private Node head;
	  private Node tail;
	  private int size = 0;
	  private class Node {
		  private Object data;
		  private Node next;
		  public Node(Object input) {
			  this.data = input;
			  this.next = null;
		  }
	  }
	  
	  public void push(Object input) {
		  Node newNode = new Node(input);
		  newNode.next = head;
		  
		  head = newNode;
		  size++;
		  if(head.next == null) {
			  tail = head;
		  }
	  }

	  public Object peek() {
		  if(head == null) {
			  return 0;
		  }
		  Node temp = head;
		  return temp.data;
	  }	  
	  
	  public Object pop() {
		  Node temp = head;
		  head = temp.next;
		  Object returnData = temp.data;
		  temp = null;
		  size--;
		  return returnData;
	  }
	  
	  public void removeTop() {
		  head = head.next;
		  size--;
	  }
	  
	  public int size() {
		  return size;
	  }
	  
	  
}