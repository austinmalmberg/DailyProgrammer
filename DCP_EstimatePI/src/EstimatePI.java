import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This problem was asked by Google.
 * 
 * The area of a circle is defined as π * r^2. Estimate π to 3 decimal places using a Monte Carlo method.
 * 
 * Hint: The basic equation of a circle is x^2 + y^2 = r^2.
 * 
 * @author Austin Malmberg
 *
 */
public class EstimatePI {
	
	/**
	 * Using a unit circle, r = 1 therefore Area = π * (1)^2 = π
	 * In the quadrant with all positive values, Area = π/4
	 * 
	 * Find π by distributing randomized points among this quadrant and finding
	 * the ratio of points that fall within the circle (have a radius < 1) to the total points.
	 * 
	 * Finally, multiply by 4 to account for the other quadrants to find π.
	 */
	
	public static final int POINTS = 20000000;	// 20 million
	
	Random random;
	Stream<DoublePoint> points;
	
	public EstimatePI() {
		random = new Random();
		
		points = IntStream.range(0, POINTS).mapToObj(i -> randomPoint());
	}
	
	public double calculatePi() {
		return ((double) points.filter(DoublePoint::inCircle).count()) / POINTS * 4;
	}
	
	public DoublePoint randomPoint() {
		return new DoublePoint(random.nextDouble(), random.nextDouble());
	}
	
	
	public static void main(String[] args) {
		EstimatePI estimatePI = new EstimatePI();
		
		System.out.println(estimatePI.calculatePi());
	}
}

class DoublePoint {
	
	double x;
	double y;
	
	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getRadius() { return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); }
	public boolean inCircle() { return getRadius() < 1; }
}
