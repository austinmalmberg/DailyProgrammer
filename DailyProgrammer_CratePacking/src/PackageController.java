import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
	crate	{ 10 15 21 }
	box	{ 12  4  5 }
	
	======
	
	crate	{ 10 15 21 }
	box	{ 12  5	 4 }
	
	======
	
	swap() =>
	
	crate	{ 10 15 21 }
	box	{ 5  12  4 }
	
	
	crate	{  6 10 12 15 }
	box	{ 12  8  4  4 }
	fixed	{  4  8 12  4 }
 */

public class PackageController {

	public static final int NO_ROTATION = 0;
	public static final int ROTATE_90 = 1;
	
	public int rotationAllowed;
	
	public PackageController()
	{
		setRotation(NO_ROTATION);
	}
	
	public void setRotation(int rotationAllowed)
	{
		this.rotationAllowed = rotationAllowed;
	}
	
	public int maxBoxesInCrate(int[] crate, int[] box)
	{
		if (rotationAllowed == ROTATE_90)
		{
			Arrays.sort(crate);
			reverseSort(box);
			
			int largestCrateDim = crate[crate.length - 1];
			int smallestBoxDim = box[0];
			
			if (largestCrateDim < smallestBoxDim)
				return 0;
			
			/*
			crate	{  6 10 12 15 }
			box		{ 12  8  4  4 }
			fixed	{  4  8 12  4 }
			*/
			
			for (int i = 0; i < box.length; i++)
			{
				if (box[i] > crate[i])
				{
					if (i == box.length - 1) return 0;
					for (int j = i + 1; j < box.length; j++)
					{
						
					}
				}
			}
		}
		
		Stream<int[]> zip = zipped(crate, box);
		return zip.mapToInt(z -> floorDiv(z[0], z[1])).reduce(1, (total, next) -> total * next);
	}
	
	public void reverseSort(int[] arr)
	{
		Arrays.sort(arr);
		
		int half = floorDiv(arr.length, 2);
		for (int i = 0; i < half; i++)
		{
			swap(arr, i, arr.length - 1 - i);
		}
	}
	
	public void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public void cascadeSwap(int[] arr, int from, int to)
	{
		int temp = arr[from];
		for(int i = to; i >= from; i--)
		{
			int temp2 = arr[i];
			arr[i] = temp;
			temp = temp2;
		}
	}
	
	private int floorDiv(int a, int b)
	{
		return Math.floorDiv(a, b);
	}
	
	private Stream<int[]> zipped(int[] ...arrs)
	{		
		return IntStream.range(0, getMin(Stream.of(arrs).mapToInt(arr -> arr.length).toArray()))
			.mapToObj(i -> Stream.of(arrs).mapToInt(arr -> arr[i]).toArray());
	}
	
	private int getMin(int ...is)
	{
		if (is.length == 0)
			return 0;
		else if (is.length == 1)
			return is[0];
		
		return IntStream.of(is).reduce(is[0], (min, curr) -> Math.min(min, curr));
	}
}
