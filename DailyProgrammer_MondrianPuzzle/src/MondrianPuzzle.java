import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MondrianPuzzle {

	private final int canvas_length;
	
	// map<key=area, val= map<key=length, val=Square>> 
	private Map<Integer, Map<Integer, Square>> squares;
	
	/**
	 * @param length the length and width of the canvas
	 */
	public MondrianPuzzle(int length) {
		this.canvas_length = length;
		
//		squares = getAreaMap(length);
	}
	
	public void solve() {
		
		// generate possible square
		// cover the canvas in squares
			// do the squares cover the entire canvas?
				// add to list
					// get 
	}
}
