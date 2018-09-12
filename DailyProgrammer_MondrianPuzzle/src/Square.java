
public class Square implements Comparable<Square> {

	private final int length, width;
	
	public Square(int l, int w) {
		this.length = l;
		this.width = w;
	}

	public int getArea() { return length * width; } 
	
	@Override
	public int compareTo(Square other) {
		return this.getArea() - other.getArea();
	}
	
}
