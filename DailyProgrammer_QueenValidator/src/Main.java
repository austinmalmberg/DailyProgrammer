import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * https://www.reddit.com/r/dailyprogrammer/comments/ab9mn7/20181231_challenge_371_easy_n_queens_validator/
 * 
 * @author Austin Malmberg
 *
 */
public class Main {
	
	public static void main(String[] args) {
		qCheck(new int[]{4, 2, 7, 3, 6, 8, 5, 1});	// true
		qCheck(new int[]{2, 5, 7, 4, 1, 8, 6, 3});	// true
		qCheck(new int[]{5, 3, 1, 4, 2, 8, 6, 3});	// false (same row)
		qCheck(new int[]{5, 8, 2, 4, 7, 1, 3, 6});	// false (same diagonal)
		qCheck(new int[]{4, 3, 1, 8, 1, 3, 5, 2});	// false
	}
	
	public static void qCheck(int[] queens) {
		// using sets and a for loop
		
		HashSet<Integer> rSet = new HashSet<>();
		HashSet<Integer> iSet = new HashSet<>();
		HashSet<Integer> dSet = new HashSet<>();
		
		boolean passed = true;
		
		for(int i = 0; i < queens.length; i++) {
			if(!(rSet.add(queens[i]) && iSet.add(queens[i]+i) && dSet.add(queens[i]-i))) {
				passed = false;
				break;
			}
		}
		
		System.out.printf("%s => %s%n", Arrays.toString(queens), passed);
		
		
		// using streams and an int array
		
//		final int VALUE = 0;
//		final int INCR = 1;
//		final int DECR = 2;
//		
//		List<int[]> v2 = IntStream.range(0, queens.length).mapToObj(i -> new int[] {queens[i], queens[i] + i, queens[i] - i}).collect(Collectors.toList());
//		
//		boolean rConf = v2.stream().map(arr -> arr[VALUE]).distinct().count() != queens.length;
//		boolean dConf = v2.stream().map(arr -> arr[INCR]).distinct().count() != queens.length ||
//				v2.stream().map(arr -> arr[DECR]).distinct().count() != queens.length;
//		 		
//		System.out.printf("%s => %s%n", Arrays.toString(queens), !(rConf || dConf));		
	}
}
