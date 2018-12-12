import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
/**
 * This problem was asked by Uber.
 * 
 * Given an array of integers, return a new array such that each element at index i of the new array
 * is the product of all the numbers in the original array except the one at i.
 * 
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 * 
 * Follow-up: what if you can't use division?
 * 
 * @author Austin Malmberg
 *
 */
public class Main {

	public static void main(String[] args) {
		int[] arr0 = {1, 2, 3, 4, 5};
		int[] arr1 = {3, 2, 1};
		
		System.out.printf("%s -> %s%n", Arrays.toString(arr0), Arrays.toString(getOtherProducts(arr0)));
		System.out.printf("%s -> %s%n", Arrays.toString(arr1), Arrays.toString(getOtherProducts(arr1)));
	}
	
	/**
	 * Takes an array and generates a new array where each element is a product of all other elements in the given array. 
	 * 
	 * Big O is O(n)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] getOtherProducts(int[] arr) {
		int[] out = new int[arr.length];
		int product = IntStream.of(arr).reduce(1, (i, j) -> i * j);
		
		for(int i = 0; i < arr.length; i++) {
			
			if(arr[i] == 0)
				out[i] = arr[i];
			else
				out[i] = product / arr[i]; 
		}
		
		return out;
	}
	
	/**
	 * Takes an array and generates a new array where each element is a product of all other elements in the given array.
	 * 
	 * Does not use division 
	 * 
	 * Big O is O(n^2)
	 * 
	 * @param arr An array of integers
	 * @return
	 */
	public static int[] getOtherProducts_noDivision(int[] arr) {
		int[] out = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			out[i] = getProduct(arr, i);
		}
		
		return out;
	}
	
	public static int getProduct(int[] arr, int n) {
		int out = 1;
		for(int i = 0; i < arr.length; i++) {
			if(i != n)
				out *= arr[i];
		}
		
		return out;
	}
}
