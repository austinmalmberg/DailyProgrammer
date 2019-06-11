import java.util.Arrays;

public class MatrixRotation {
	
	public static void main(String[] args) {
		int[][] arr1 = {
				{4, 1, 3, 2},
				{1, 3, 4, 2},
				{2, 4, 1, 3},
				{1, 2, 3, 4}
		};
		
		int[][] arr2 = {
				{4, 1, 3, 2},
				{1, 3, 4, 2},
				{2, 4, 1, 3},
				{1, 2, 3, 4}
		};
		
		// print array
		System.out.println("Original:");
		for(int[] row : arr1) {
			System.out.println(Arrays.toString(row));
		}
		
		System.out.println();
		
		int[][] rotated = rotate90(arr1);
		
		// print array
		for(int[] row : rotated) {
			System.out.println(Arrays.toString(row));
		}
		
		System.out.println();
		
		// print array
		rotate90(arr2, arr2.length);
		for(int[] row : arr2) {
			System.out.println(Arrays.toString(row));
		}
	}
	
	public static void rotate90(int[][] arr, int len) {
		
		for(int i = 0; i < len/2; i++) {
			
			for(int j = i; j < len-1 - i; j++) {
				swapCorners(arr, len, i, j);
			}
		}
	}
	
	public static void swapCorners(int[][] arr, int len, int row, int col) {
		int lastIndex = len-1;
		
		int temp = arr[row][col];
		arr[row][col] = arr[lastIndex-col][row];
		arr[lastIndex-col][row] = arr[lastIndex-row][lastIndex-col];
		arr[lastIndex-row][lastIndex-col] = arr[col][lastIndex-row];
		arr[col][lastIndex-row] = temp;
	}
	
	public static int[][] rotate90(int[][] arr) {
		int[][] out = new int[arr.length][];
		
		for(int i = 0; i < arr.length; i++) {
			out[i] = getColumn(arr, arr[i].length, i);
		}
		
		return out;
	}
	
	public static int[] getColumn(int[][] arr, int len, int pos) {
		int[] out = new int[len];
		
		for(int i = 0; i < len; i++) {
			out[i] = arr[len-1 - i][pos];
		}
		
		return out;
	}
}
