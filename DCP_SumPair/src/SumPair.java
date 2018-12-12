import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This problem was recently asked by Google.
 * 
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 * 
 * Bonus: Can you do this in one pass?
 * 
 * 
 * @author Austin Malmberg
 *
 */
public class SumPair {

	public static void main(String[] args) {
		int[] arr0 = {10, 15, 3, 7};
		int sum0 = 17;
		
		int[] arr1 = {1, 2, 3, 9};
		int sum1 = 8;
		
		int[] arr2 = {1, 2, 4, 4};
		int sum2 = 8;
		
		System.out.printf("%s to equal %d -> %s%n", Arrays.toString(arr0), sum0, containsPairEqualToSum(arr0, sum0));
		System.out.printf("%s to equal %d -> %s%n", Arrays.toString(arr1), sum1, containsPairEqualToSum(arr1, sum1));
		System.out.printf("%s to equal %d -> %s%n", Arrays.toString(arr2), sum2, containsPairEqualToSum(arr2, sum2));
	}
	
	public static boolean containsPairEqualToSum(int[] arr, int sum) {
		// worst case-- O(n) time complexity
		Set<Integer> set = new HashSet<>();
		set.add(arr[0]);
		for(int i = 1; i < arr.length; i++) {
			if(set.contains(sum - arr[i]))
				return true;
			
			set.add(arr[i]);
		}
		
		return false;
	}
}
