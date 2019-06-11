import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main
{
	public static void main(String[] args)
	{
		
		int[][] examples2DRotationNone = {
				{25, 18, 6, 5}, // => 12
				{10, 10, 1, 1}, // => 100
				{12, 34, 5, 6}, // => 10
				{12345, 678910, 1112, 1314}, // => 5676
				{5, 100, 6, 1} // => 0
		};
		
		int[][] examples2DRotationAll = {
				{25, 18, 6, 5}, // => 15
				{12, 34, 5, 6}, // => 12
				{12345, 678910, 1112, 1314}, // => 5676
				{5, 5, 3, 2}, // => 2
				{5, 100, 6, 1}, // => 80
				{5, 5, 6, 1} // => 0
		};
		
		
		int[][] examples3D = {
				{10, 10, 10, 1, 1, 1}, // => 1000
				{12, 34, 56, 7, 8, 9}, // => 32
				{123, 456, 789, 10, 11, 12}, // => 32604
				{1234567, 89101112, 13141516, 171819, 202122, 232425} // => 174648
		};
		
		
		int[][] examplesND = {
				{3, 4, 1, 2}, // => 6
				{123, 456, 789, 10, 11, 12}, // => 32604
				{123, 456, 789, 1011, 1213, 1415, 16, 17, 18, 19, 20, 21}
		};
		 
//		CratePacking pack = new CratePacking();
//		
//		System.out.println("----- NO ROTATION -----");
//		Stream.of(examples2DRotationNone).forEach(pack::displayMaxBoxes);
//		
//		
//		pack.setRotation(CratePacking.ALL_ROTATE);
//		
//		
//		System.out.println("----- ALL ROTATION -----");
//		Stream.of(examples2DRotationAll).forEach(pack::displayMaxBoxes);
//		
//		
//		System.out.println("----- ALL ROTATION | 3D -----");
//		Stream.of(examples3D).forEach(pack::displayMaxBoxes);
//		
//		System.out.println("----- ALL ROTATION | ND -----");
//		Stream.of(examplesND).forEach(pack::displayMaxBoxes);
		
		PackageController packControl = new PackageController();
		System.out.println("----- NO ROTATION -----");
		Stream.of(examples2DRotationNone).forEach(ex -> {
			
			int half = Math.floorDiv(ex.length, 2) + (ex.length % 2 == 0 ? 0 : 1);
			
			int[] crateDimensions = Arrays.copyOfRange(ex, 0, half);
			int[] boxDimensions = Arrays.copyOfRange(ex, half, ex.length);
			
			System.out.println(packControl.maxBoxesInCrate(crateDimensions, boxDimensions));
		});
		
		int[] example = { 12, 15, 6, 17, 3 };
		System.out.println(Arrays.toString(example));
		
		packControl.cascadeSwap(example, 0, 3);
		
		System.out.println(Arrays.toString(example));
	}
	
	public static int[] rotateCopy(int[] arr, int pos)
	{
		if(pos == 0 || pos == arr.length) return arr;
		
		return IntStream.concat(
				IntStream.of(Arrays.copyOfRange(arr, pos, arr.length)),
				IntStream.of(Arrays.copyOfRange(arr, 0, pos))).toArray();
	}
}
