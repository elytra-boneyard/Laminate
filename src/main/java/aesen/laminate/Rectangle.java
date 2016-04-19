package aesen.laminate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Objects;

/**
 * Specifies a bound on a two-dimensional plane, with a position and size.
 * <p>
 * Most javadocs on this class use an X11-esque geometry format for describing
 * rectangles, in format &lt;width&gt;x&lt;height&gt;[+&lt;x&gt;,&lt;y&gt;].
 *
 */
public class Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;
	
	/**
	 * Creates a Rectangle and initializes it to 0x0+0,0
	 */
	public Rectangle() {
		this(0, 0, 0, 0); // may as well be explicit
	}
	
	/**
	 * Creates a Rectangle and initializes it to the size given,
	 * with a position of 0, 0.
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 */
	public Rectangle(int width, int height) {
		this(0, 0, width, height);
	}
	
	/**
	 * Creates a Rectangle and initializes it to the parameters given.
	 * @param x the X position of the rectangle
	 * @param y the Y position of the rectangle
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Converts this Rectangle into an X11-esque geometry string, in format
	 * &lt;width&gt;x&lt;height&gt;[+&lt;x&gt;,&lt;y&gt;]. For example, a rectangle
	 * at X 4 and Y 8, that is 20 wide by 40 high would be 20x40+4,8. A rectangle
	 * at X 0 and Y 0 with the same size would be 20x40, without a position.
	 */
	@Override
	public String toString() {
		if (x == 0 && y == 0) {
			return width+"x"+height;
		}
		return width+"x"+height+"+"+x+","+y;
	}
	
	private static final Pattern GEOMETRY_PATTERN = Pattern.compile("^([0-9]+)x([0-9]+)(?:\\+([0-9]+),([0-9]+))?$");
	
	/**
	 * Creates a Rectangle from a geometry string, potentially created by {@link #toString}.
	 * See that method for a description of the geometry string.
	 * @param geom a geometry string to parse
	 * @throws IllegalArgumentException if the passed string is not a valid geometry string
	 * @return a new Rectangle matching the geometry string passed
	 * @see #toString()
	 */
	public static Rectangle fromString(String geom) {
		Matcher matcher = GEOMETRY_PATTERN.matcher(geom);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("'"+geom+"' is not a valid geometry string");
		}
		int x = Integer.parseInt(Objects.firstNonNull(matcher.group(3), "0"));
		int y = Integer.parseInt(Objects.firstNonNull(matcher.group(4), "0"));
		int width = Integer.parseInt(matcher.group(1));
		int height = Integer.parseInt(matcher.group(2));
		return new Rectangle(x, y, width, height);
	}
}
