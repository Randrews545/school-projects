
public class Rectangle {
	
	/** Rectangle fields (aka State variables) **/
	Point origin = new Point();
	double width;
	double height;
	
	/** Constructor **/
	/**
	 * Rectangle Constructor
	 * @param initialX - Origin point's X value 
	 * @param initialY - Origin point's Y value
	 * @param initialWidth - Rectangle width
	 * @param initialHeight - Rectangle height
	 */
	public Rectangle(double initialX, double initialY, double initialWidth, double initialHeight) {
		origin.x = initialX;
		origin.y = initialY;
		width = initialWidth;
		height = initialHeight;
	}        
	
	// Accessor Method: Returns the area of the rectangle
	public double area() {
		return (width * height);
	}
	
	// Accessor Method: Returns the perimeter of the rectangle
	public double perimeter() {
		return (2 * (width + height)); 
	}
	
	// Accessor Method: Returns the points of the rectangle
	public Point[] getPoints() {
		Point[] points = new Point[4];
			points[0] = new Point(origin.x, origin.y);
			points[1] = new Point(width, origin.y);
			points[2] = new Point(origin.y, origin.y - height);
			points[3] = new Point(origin.x, origin.y - height);
		return points;
	}
	
	// Accessor Method: Returns a string representation of this rectangle
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
