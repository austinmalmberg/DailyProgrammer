import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Memoize rectangles, storing combinations of smaller rectangles that make up the larger one. 
 * 
 * @author Austin Malmberg
 */
public class Memoization {

	private Map<Rectangle, List<Set<Rectangle>>> memo;
	
	private Combinations combos;
	
	public Memoization() {
		memo = new HashMap<>();
	}
	
	public List<Set<Rectangle>> get(Rectangle r) {
		return memo.get(r);
	}
	
	public void add(Rectangle r) {
		if(memo.containsKey(r))
			return;
		
		memo.put(r, getAllRectangles(r));
		
//		memo.put(r, generateCombinations(inner));
	}
	
	public List<Set<Rectangle>> getAllPossibleSets() {
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	public List<Set<Rectangle>> getAllRectangles(Rectangle r) {
		
		// pairs or rectangles split vertically
		List<Set<Rectangle>> all = getInnerRectangles(r).collect(Collectors.toList());
		
		// rotate vertical pairs aka split r horizontally
		Stream<Set<Rectangle>> rotated = all.stream().map(set -> set.stream().map(Rectangle::rotate).collect(Collectors.toSet()));
		
		// add the rotated sets to the list
		all.addAll(rotated.collect(Collectors.toList()));
		
		// add the original rectangle (as a set) 
		all.add(Stream.of(r).collect(Collectors.toSet()));
		
		return all;
	}
	
	private Stream<Set<Rectangle>> getInnerRectangles(Rectangle r) {
		int i = (r.getLength() % 2 == 0 ? r.getLength()-1 : r.getLength()) / 2;
		return IntStream.rangeClosed(1, i)
						.mapToObj(r1length -> splitRectangle(r.getHeight(), r1length, r.getLength()-r1length)
								.collect(Collectors.toSet()));
	}
	
	private Stream<Rectangle> splitRectangle(int height, int...lengths) {		
		return IntStream.of(lengths).mapToObj(l -> new Rectangle(l, height));
	}
	
	public int getLimit(int i) {
		return (i % 2 == 0 ? i - 1 : i) / 2;
	}
}
