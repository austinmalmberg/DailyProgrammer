
public class Rectangle implements Comparable<Rectangle> {

	private final int length, height;
	
	public Rectangle(int length) {
		this.length = this.height = length;
	}
	
	public Rectangle(int l, int h) {
		this.length = l;
		this.height = h;
	}

	public int getLength() { return length; }
	public int getHeight() { return height; }
	public int getArea() { return length * height; } 
	
	@Override
	public int compareTo(Rectangle other) {
		return this.getArea() - other.getArea();
	}
	
	@Override
	public String toString() {
		return String.format("[l=%d, h=%d]", length, height);
	}
	
	public Rectangle rotate() { return new Rectangle(height, length); }
}
