import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This problem was asked by Stripe.
 * 
 * Given an array of integers, find the first missing positive integer in linear time and constant space.
 * In other words, find the lowest positive integer that does not exist in the array. The array can
 * contain duplicates and negative numbers as well.
 * 
 * For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
 * 
 * You can modify the input array in-place.

 * @author Austin Malmberg
 *
 */
public class Main {

	public static void main(String[] args) {
		int[] arr1 = {3, 4, -1, 1};
		int[] arr2 = {0, 6, 5, 4, 3, 2, 1};
		int[] arr3 = {-6, 4, 1, 4, -5, 9, 10, 8, 4, 3, -3, 1, 2, -4, 6};
		
//		System.out.println(getLowestPostiveMissingInteger(arr1));
//		System.out.println(getLowestPostiveMissingInteger(arr2));
//		System.out.println(getLowestPostiveMissingInteger(arr3));
		
		System.out.println(findLowestMissingPositiveInteger_modifiesArray(arr1));
		System.out.println(findLowestMissingPositiveInteger_modifiesArray(arr2));
		System.out.println(findLowestMissingPositiveInteger_modifiesArray(arr3));
	}
	
	private static int getLowestPostiveMissingInteger(int[] arr) {
		if(arr.length == 0) return 0;
		
		Set<Integer> set = IntStream.of(arr).boxed().collect(Collectors.toCollection(HashSet::new));
		
		System.out.println(set.toString());
		
		int low = 1;
		while(set.contains(low))
			low++;
		
		return low;
	}
	
	/**
	 * Find and returns the lowest positive integer missing from the given array.
	 * 
	 * Also modifies the array.
	 * 
	 * @param arr
	 * @return
	 */
	private static int findLowestMissingPositiveInteger_modifiesArray(int[] arr) {
		int firstPositiveNum = segregate(arr);
		
		/**
		 * Loops through all positive numbers.  If the number falls within the array, it's **position**
		 * (with respects to the index of the first positive number) is denoted with a negative (-) sign.
		 */
		for(int i = firstPositiveNum; i < arr.length; i++) {
			
			int positiveValue = Math.abs(arr[i]);
			setValueToNegative(arr, positiveValue + firstPositiveNum-1);
		}
		
		
		
		/**
		 * Loops through the array and returns the first missing positive number by finding the index
		 * of the first positive number shifted with respects to the index of the first positive number.
		 */
		for(int i = firstPositiveNum; i < arr.length; i++) {
			
			if(arr[i] > 0)
				return i+1 - firstPositiveNum;
		}
		
		return arr.length+1 - firstPositiveNum;
	}
	
	/**
	 * Checks to see if the index exists within the array then sets the value at the given index to negative.
	 * 
	 * @param arr An array
	 * @param index The index of the array to change to negative
	 */
	private static void setValueToNegative(int[] arr, int index) {
		if(index < 0 || index >= arr.length)
			return;
		
		arr[index] = Math.abs(arr[index]) * -1;
	}
	
	
	/**
	 * Put all non-positive numbers on the left side of the array and returns the index of the first positive number.
	 * 
	 * @param arr An array to segregate
	 * @return The index of the first positive integer in the array
	 */
	private static int segregate(int[] arr) {
		int indexFirstPositiveInteger = 0;
		
		for(int i = 0; i < arr.length; i++) {
			
			if(arr[i] < 1) {
				swap(arr, indexFirstPositiveInteger, i);
				indexFirstPositiveInteger++;
			}
		}
		
		return indexFirstPositiveInteger;
	}
	
	private static void swap(int[] arr, int pos1, int pos2) {
		if(pos1 == pos2) return;
		
		int temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
}
