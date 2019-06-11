import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This problem was asked by Facebook.
 * 
 * Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.
 * 
 * @author Austin Malmberg
 *
 */

public class RandomElementSelector<T> {

	private Random random;
	private T theChosenOne;
	private int size;
	
	public RandomElementSelector() {
		random = new Random();
		
		size = 0;
	}
	
	/**
	 * @param stream
	 * @return A random element from a stream with uniform probability
	 */
	public T get(Stream<T> stream) {
		theChosenOne = null;
		size = 0;
		
		stream.forEach(t -> {
			if(isChosen(++size)) {
				theChosenOne = t;
			}
		});
		
		return theChosenOne;
	}
	
	/**
	 * 
	 * @param outcomes The number of possible outcomes
	 * @return True if a randomly selected integer is equal to the lowest possible value
	 * and false otherwise
	 */
	private boolean isChosen(int outcomes) {
		return random.nextInt(outcomes) == 0; // works just as well with == size-1;
	}
	
	public static void main(String[] args) {		
		int[] is = {4, 1, 2, 43, 5, 3, 3, 6, 7, 8, 4, 43, 3, 23, 2, 6, 5, 2, 3};
		Stream<Integer> stream = IntStream.of(is).boxed();
		
		RandomElementSelector<Integer> rse = new RandomElementSelector<>();
		System.out.println(rse.get(stream));
	}
}
