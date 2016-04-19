package aesen.laminate.component;

import com.unascribed.laminate.internal.GL;
import aesen.laminate.Container;
import aesen.laminate.Rectangle;

/**
 * The base of all Laminate components. Doesn't do much on it's
 * own, but supplies a solid base for writing other components.
 *
 */
public abstract class Component extends Container {
	protected int x;
	protected int y;

	public final void render(float partialTicks) {
		GL.pushMatrix();
			GL.pushScissor();
				GL.translate(x, y, 0);
				GL.setScissorFromComponentCoordinates(x, y, width, height);
				doRender(partialTicks);
				
				GL.translate(getSafeZoneX(), getSafeZoneY(), 0);
				GL.setScissorFromComponentCoordinates(
						x+getSafeZoneX(), y+getSafeZoneY(),
						getSafeZoneWidth(), getSafeZoneHeight());
				renderChildren(partialTicks);
			GL.popScissor();
		GL.popMatrix();
	}
	
	protected abstract void doRender(float partialTicks);
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns the X coordinate of the upper-left corner of the "safe zone",
	 * that being the region this component dedicates to external rendering,
	 * such as, <i>but not only</i>, children. Relative to the component's X,
	 * defaulting to 0. This can be used to enforce borders around a container,
	 * without translating and modifying the scissor box yourself.
	 * <p>
	 * It is bad practice to set this to something outside of the component's
	 * box in the effort to prevent the component from having children or
	 * external rendering; all Laminate components should allow external
	 * rendering, even if you don't think it makes sense.
	 * 
	 * @return The X coordinate of this component's "safe zone", relative to its
	 *         base X coordinate.
	 */
	public int getSafeZoneX() {
		return 0;
	}
	
	/**
	 * @see #getSafeZoneX() getSafeZoneX(), for a description of the safe zone
	 * @return The Y coordinate of this component's "safe zone", relative to its
	 *         base Y coordinate.
	 */
	public int getSafeZoneY() {
		return 0;
	}
	
	/**
	 * @see #getSafeZoneX() getSafeZoneX(), for a description of the safe zone
	 * @return The width of this component's "safe zone".
	 */
	public int getSafeZoneWidth() {
		return getWidth();
	}
	
	/**
	 * @see #getSafeZoneX() getSafeZoneX(), for a description of the safe zone
	 * @return The height of this component's "safe zone".
	 */
	public int getSafeZoneHeight() {
		return getHeight();
	}
	
	
	public Rectangle toRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
}
