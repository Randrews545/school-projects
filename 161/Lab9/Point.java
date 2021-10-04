
public class Point {
	
	/** Fields (aka State variables) **/
	double x;
	double y;
	
	
	/** Constructors **/
	
	// constructs a point with a location of 0, 0
	public Point() {
		x = 0;
		y = 0;
	}
	
	// constructs a new point with the given (x, y) location
	public Point(double initialX, double initialY) {
		x = initialX;
		y = initialY;
	}
	
	
	/** Methods (Behavior of the class) **/
	
	// Accessor Method: Returns the area of the Point (points have no area)
	public double area() {
		return (0.0);
	}
	
	// Accessor Method: Return perimeter of the Point (points have no perimeter)
	public double perimeter() {
		return ( 0.0 ); 
	}
	
	// Accessor Method: Returns the single point
	public Point[] getPoints() {
		Point[] points = new Point[1];   // Just one point for a Point
		points[0] = this;  // this refers to this object
		return points;
	}
	
	// Accessor Method: Returns a String representation of this Point
	public String toString() {
		return String.format("(%.2f, %.2f)", x, y);
	}
	
}
