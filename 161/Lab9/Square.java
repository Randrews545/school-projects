
public class Square {
	
	/** Square fields (aka State variables) **/
	Point origin = new Point();
	double size;
	
	/** Constructor **/
	/**
	 * Square constructor
	 * @param initialX - Origin point's X value
	 * @param initialY - Origin point's Y value
	 * @param initialSize - Size of the Square
	 */
	public Square(double initialX, double initialY, double initialSize) {
		origin.x = initialX;
		origin.y = initialY;
		size = initialSize;
	}                
	
	// Accessor Method: Returns the area of the square
	public double area() {
		return (size * size);
	}
	
	// Accessor Method: Returns the perimeter of the square
	public double perimeter() {
		return (size * 4); 
	}
	
	// Accessor Method: Returns the points of the square
	public Point[] getPoints() {
		Point[] points = new Point[4];
			points[0] = new Point(origin.x, origin.y);
			points[1] = new Point(size, origin.y);
			points[2] = new Point(size, origin.y - size);
			points[3] = new Point(origin.x, origin.y - size);
		return points;
	}
	
	// Accessor Method: Returns a string representation of this square
	public String toString() {
		String ret = "[";	// Start with array bracket
		Point[] points = getPoints();  // get points of the shape
		for (int i=0; i < points.length; i++) {  // loop to add to ret string
			ret += points[i].toString();
			if (i+1 < points.length) {   // fence-post variant
				ret += ", ";
			}
		}
		ret += "]";  // Close out array bracket
		return ret; 
	}
}
