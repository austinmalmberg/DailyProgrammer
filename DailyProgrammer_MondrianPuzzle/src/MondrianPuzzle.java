import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;

public class MondrianPuzzle {
	
	private Memoization memo;
	
	public MondrianPuzzle() {
		memo = new Memoization();
	}
	
	public Set<Rectangle> solve(int canvasLength) {
		Rectangle canvas = new Rectangle(canvasLength);
		
		memo.add(canvas);
		
		return memo.get(canvas).stream().min(new Comparator<Set<Rectangle>>() {
			
			@Override
			public int compare(Set<Rectangle> o1, Set<Rectangle> o2) {
				return calcScore(o1) - calcScore(o2);
			}
		}).orElse(null);
	}
	
	/**
	 * @return The area of the largest rectangle minus the area of the smallest rectangle
	 */
	private int calcScore(Set<Rectangle> set) {
		if(set.isEmpty()) return Integer.MAX_VALUE;
		if(set.size() == 1) return set.stream().findFirst().get().getArea() - 0;
		
		IntSummaryStatistics stats = set.stream()
				.mapToInt(Rectangle::getArea).summaryStatistics();
		
		return stats.getMax() - stats.getMin();
	}
}
