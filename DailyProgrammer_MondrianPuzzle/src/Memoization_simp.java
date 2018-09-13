import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Memoize rectangles, storing combinations of smaller rectangles that make up the larger one. 
 * 
 * @author Austin Malmberg
 */
public class Memoization_simp {

	private Map<Rectangle, List<Set<Rectangle>>> memo;
	
	private Combinations combos;
	
	public Memoization_simp() {
		memo = new HashMap<>();
		
		Rectangle oneByOne = new Rectangle(1);
		memo.put(oneByOne, rectanglesAsSetList(oneByOne));
	}
	
	public List<Set<Rectangle>> get(Rectangle r) {
		return memo.get(r);
	}
	
	public void add(Rectangle r) {
		if(memo.containsKey(r))
			return;		
	}
	
	public void simplifyAndAdd(Rectangle r) {
		
	}
	
	public List<Set<Rectangle>> rectanglesAsSetList(Rectangle...Rs) {
		return Stream.of(Stream.of(Rs).collect(Collectors.toSet())).collect(Collectors.toList());
	}
}
