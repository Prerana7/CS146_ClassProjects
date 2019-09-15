package cs146F19.Sunilkumar.project1;
import java.util.*;

public class Prisoners {
	
	private static Node head;
	private static Node tail;
	
	public Prisoners(int n) {
		Node prev = null;
		for(int i=1; i<=n; i++) {
			Node prisoner = new Node(i);
			if(i==1) {
				head = prisoner;
				prev = prisoner;
			} else {
				if(i==n) {
					tail = prisoner;
					prisoner.setNext(head);
					head.setPrev(prisoner);
					prisoner.setPrev(prev);
					prev.setNext(prisoner);
				} else {
					prisoner.setPrev(prev);
					prev.setNext(prisoner);
					prev = prisoner;
				}
			}
		}
	}
	
	//eliminating the prisoners based on the values of n and k
	public static int play(int n, int k) {
		
		Prisoners p = new Prisoners(n);
		
		int total = n;
		Node temp = p.head;
		while(total>1) {
			for(int i=1; i<k; i++) {
				temp = temp.getNext();
			}
			Node delete = temp.getNext();
			if(total==2)
				break;
			temp.setNext(delete.getNext());
			delete.getNext().setPrev(temp);
			total = total-1;
			temp = temp.getNext();
		}
		
		return temp.getData();
	}
	
	//deleting the node with the specified content
	public static void delete(int content) {
		if(head.getData()==content) {
			tail.setNext(head.getNext());
			head.getNext().setPrev(tail);
			head = head.getNext();
		} else {
			if(tail.getData()==content) {
				tail.getPrev().setNext(head);
				head.setPrev(tail.getPrev());
				tail = tail.getPrev();
			} else {
				Node temp = head;
				while(temp.getNext()!=tail) {
					temp = temp.getNext();
					if(temp.getData()==content) {
						temp.getPrev().setNext(temp.getNext());
						temp.getNext().setPrev(temp.getPrev());
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		long l5 = System.currentTimeMillis();
		Prisoners p = new Prisoners(5);
		long l6 = System.currentTimeMillis();
		System.out.println("time taken to create the linked list: "+(l6-l5));
		
		long l3 = System.currentTimeMillis();
		p.delete(3);
		long l4 = System.currentTimeMillis();
		System.out.println("time taken to delete a node: "+(l4-l3));

		long l1 = System.currentTimeMillis();
		play(6, 2);
		long l2 = System.currentTimeMillis();
		System.out.println("time taken to eliminate the prisoners: "+(l2-l1-(l6-l5)));
	}
	
}
