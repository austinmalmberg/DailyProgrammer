import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CratePacking {

	public static final int NO_ROTATE = 0;
	public static final int ALL_ROTATE = 1;
	
	public int rotationOptions;
	
	public CratePacking() {
		rotationOptions = NO_ROTATE;
	}
	
	public CratePacking(int rotationOptions)
	{
		setRotation(rotationOptions);
	}
	
	public void setRotation(int rotationOptions)
	{
		this.rotationOptions = rotationOptions;
	}
	
	public int maxPackedBoxes(int[] crate, int[] box)
	{
		if(rotationOptions == NO_ROTATE)
		{
			return maxBoxes(crate, box);
		}
		else if(rotationOptions == ALL_ROTATE)
		{
			return IntStream.range(0, box.length)
					.mapToObj(i -> rotateCopy(box, i))
					.mapToInt(boxRotated -> maxBoxes(crate, boxRotated))
					.reduce(0, (max, curr) -> Math.max(max, curr));
		}
		
		return -1;
	}
	
	private int maxBoxes(int[] crate, int[] box)
	{
		int[][] zip = zipped(crate, box);
//		Stream.of(zip).map(Arrays::toString).forEach(System.out::println);
		
		return Stream.of(zip)
				.mapToInt(arr -> Math.floorDiv(arr[0], arr[1])) // finds Math.floor(zip[0] / zip[1])
				.reduce(1, (a, b) -> a * b); // multiply together
	}
	
	public void displayMaxBoxes(int[] dimensions)
	{
		int half = (int) dimensions.length / 2 + (dimensions.length % 2 == 0 ? 0 : 1);
		
		int[] crate = Arrays.copyOfRange(dimensions, 0, half);
		int[] box = Arrays.copyOfRange(dimensions, half, dimensions.length);
		
		System.out.printf("Crate dimensions: %s%n"
				+ "Box dimensions: %s%n"
				+ "Maximum boxes: %d%n%n", Arrays.toString(crate), Arrays.toString(box), maxPackedBoxes(crate, box));
	}
	
	
	private int[][] zipped(int[] ...arrs)
	{
		if (arrs.length == 1) {
			return new int[][] {arrs[0]};
		}
		
		return IntStream.range(0, getMin(Stream.of(arrs).mapToInt(arr -> arr.length).toArray()))
			.mapToObj(i -> Stream.of(arrs).mapToInt(arr -> arr[i]).toArray())
			.toArray(int[][]::new);
	}
	
	private int getMin(int ...is) {
		if (is.length == 0)
			return 0;
		else if (is.length == 1)
			return is[0];
		
		return IntStream.of(is).reduce(is[0], (min, curr) -> Math.min(min, curr));
	}
	
	private int getMax(int ...is) {
		if (is.length == 0)
			return 0;
		else if (is.length == 1)
			return is[0];
		
		return IntStream.of(is).reduce(is[0], (min, curr) -> Math.max(min, curr));
	}
	
	private int[] rotateCopy(int[] arr, int pos)
	{
		return IntStream.concat(
				IntStream.of(Arrays.copyOfRange(arr, pos, arr.length)),
				IntStream.of(Arrays.copyOfRange(arr, 0, pos))).toArray();
	}
	
	// for testing purposes
	@SuppressWarnings("unused")
	private void printRotations(int[] is) {
		// iterates through examples and prints each rotation of the arrays
		IntStream.range(0, is.length)
				.mapToObj(index -> rotateCopy(is, index))
				.map(Arrays::toString)
				.forEach(System.out::println);
	}
}
