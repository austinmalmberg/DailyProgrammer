public class Main {

	public static void main(String[] args) {
		MondrianPuzzle puzzle = new MondrianPuzzle();
		
		int canvasLength = 4;
		puzzle.solve(canvasLength).forEach(System.out::println);
	}	
}
