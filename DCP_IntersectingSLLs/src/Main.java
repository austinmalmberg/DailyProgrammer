
/**
 * This problem was asked by Google.

Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.

For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.

In this example, assume nodes with the same value are the exact same node objects.

Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.
 * 
 * @author Austin Malmberg
 *
 */
public class Main {

	public static void main(String[] args) {
		Node h1 =
				new Node(3,
					new Node(7, 
						new Node(8,
							new Node(10, null))));
		Node h2 =
				new Node(99,
					new Node(1, h1.next.next));
		
		System.out.println(intersect(h1, h2));
		System.out.println(h1.linkedListAsString());
	}
	
	public static Node intersect(Node h1, Node h2) {
		return null;
	}
}

class Node {
	
	int val;
	Node next;
	
	Node(int val, Node next) {
		this.val = val;
		this.next = next;
	}
	
	@Override
	public String toString() {
		return Integer.toString(val);
	}
	
	public String linkedListAsString() {
		if(next == null)
			return this.toString();
		
		return this.toString() + ", " + next.linkedListAsString();
	}
}
