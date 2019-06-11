import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a queue, reverse it using recursion.
 * 
 * Operations allowed:
 * 
 * enqueue(x): add an element to the end of a queue
 * dequeue(): remove and return the first element from the queue
 * empty(): checks to see whether the queue is empty
 * 
 * @author Austin Malmberg
 *
 */

public class Main {

	public static void main(String[] args) {
		ArrayDeque<Integer> queue = IntStream.range(0, 10).boxed().collect(Collectors.toCollection(ArrayDeque::new));
		
		System.out.println(queue.toString());
		
		reverse(queue);
		
		System.out.println(queue.toString());
	}
	
	public static void reverse(ArrayDeque<Integer> queue) {
		if(queue.isEmpty())
			return;
		
		Integer front = queue.pop();
		
		reverse(queue);
		
		queue.add(front);
	}
}
