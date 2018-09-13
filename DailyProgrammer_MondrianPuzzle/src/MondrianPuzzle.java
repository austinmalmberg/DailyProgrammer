import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MondrianPuzzle {
	
	private Map<Rectangle, List<Set<Rectangle>>> memo;
	
	public MondrianPuzzle() {
		memo = new HashMap<>();
		
		Rectangle oneByOne = new Rectangle(1);
		memo.put(oneByOne, rectanglesAsSetList(oneByOne));
	}
	
	private List<Set<Rectangle>> rectanglesAsSetList(Rectangle...Rs) {
		return Stream.of(Stream.of(Rs).collect(Collectors.toSet())).collect(Collectors.toList());
	}
	
	private void addToMemoTable(Rectangle r, Set<Rectangle>...sets) {
		memo.get(r).addAll(Arrays.asList(sets));
	}
	
	private void addToMemoTable(Rectangle r, List<Set<Rectangle>> sets) {
		if(!memo.containsKey(r))
			memo.put(r, Collections.emptyList());
		
		memo.put(r, Stream.concat(memo.get(r).stream(),
				sets.stream()).collect(Collectors.toList()));
	}
	
	public void printOptimalSolution(Rectangle canvas) {
		Set<Rectangle> solution = solve(canvas);
		
		System.out.printf("Score: %d%n", getScore(solution));
		
		Rectangle smallest = solution.stream().min(comparatorByArea()).orElse(null);
		System.out.printf("Smallest: %s (area=%d)%n", smallest.toString(), smallest != null ? smallest.getArea() : 0);
		
		Rectangle largest = solution.stream().max(comparatorByArea()).orElse(null);
		System.out.printf("Largest: %s (area=%d)%n", largest.toString(), largest != null ? largest.getArea() : 0);
		
		solution.forEach(System.out::println);
	}
	
	public Set<Rectangle> solve(int canvasLength) {
		return solve(new Rectangle(canvasLength));
	}
	
	public Set<Rectangle> solve(Rectangle canvas) {
		updateMemoTable(canvas);
		
		return memo.get(canvas).stream().min(comparatorByScore()).orElse(null);
	}
	
	public Comparator<Set<Rectangle>> comparatorByScore() {
		return new Comparator<Set<Rectangle>>() {

			@Override
			public int compare(Set<Rectangle> s1, Set<Rectangle> s2) {
				return getScore(s1) - getScore(s2);
			};
		};
	}
	
	public Comparator<Rectangle> comparatorByArea() {
		return new Comparator<Rectangle>() {

			@Override
			public int compare(Rectangle r1, Rectangle r2) {
				return r1.getArea() - r2.getArea();
			}
		};
	}
	
	/**
	 * @return The difference between the largest and smallest rectangles
	 */
	private int getScore(Set<Rectangle> set) {
		if(set.isEmpty()) return Integer.MAX_VALUE;
		if(set.size() == 1) return set.stream().findFirst().get().getArea() - 0;
		
		IntSummaryStatistics stats = set.stream()
				.mapToInt(Rectangle::getArea).summaryStatistics();
		
		return stats.getMax() - stats.getMin();
	}
	
	/**
	 * Verifies the Rectangle is in the memo table, otherwise adds it.
	 * 
	 * @param r
	 */
	private void updateMemoTable(Rectangle r) {
		if(memo.containsKey(r)) return;
		
		int limit = (r.getLength() % 2 == 0 ? r.getLength() - 1 : r.getLength()) / 2;  
		IntStream.rangeClosed(1, limit).mapToObj(len -> split())
	}
}
