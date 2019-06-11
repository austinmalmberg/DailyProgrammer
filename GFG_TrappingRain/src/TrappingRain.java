import java.util.Arrays;

public class TrappingRain {

	
	private static int getTotalRainCollected(int[] arr) {
		
		int total = 0;
		
		int left = 0;
		int right = arr.length-1;
		
		int fillLevel = 0;
		
		while(left < right) {
			
			if(arr[left] <= fillLevel)
				left++;
			
			if(arr[right] <= fillLevel)
				right--;
			
			if(arr[left] > fillLevel && arr[right] > fillLevel) {
				fillLevel++;
				total += addRain(arr, left+1, right, fillLevel);
			}
		}
		
		return total;
	}
	
	
	private static int addRain(int[] arr, int start, int end, int fillLevel) {
		int total = 0;
		for(int i = start; i < end; i++) {
			
			if(arr[i] < fillLevel)
				total++;
		}
		
		return total;
	}


	public static void main(String[] args) {
		int[] example0 = {2, 0, 2};
		int[] example1 = {3, 0, 0, 2, 0, 4};
		int[] example2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
		
		System.out.println(getTotalRainCollected(example0));
		System.out.println(getTotalRainCollected(example1));
		System.out.println(getTotalRainCollected(example2));
	}
	
	
}
