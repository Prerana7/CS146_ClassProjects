package cs146F19.Sunilkumar.project1;
import java.util.*;

public class Node {
	
	private int n;
	private Node next;
	private Node previous;
	
	Node(int n){
		this.n = n;
	}
	
	Node getNext() {
		return next;
	}
	
	void setNext(Node next) {
		this.next = next;
	}
	
	Node getPrev() {
		return previous;
	}
	
	void setPrev(Node previous) {
		this.previous = previous;
	}
	
	int getData() {
		return n;
	}

}
