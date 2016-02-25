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

	public final void render(float partialTicks) {GL.pushMatrix();
			GL.pushScissor();
				GL.translate(x, y, 0);
				GL.setScissorFromComponentCoordinates(x, y, width, height);
				GL.translateScissor(x, y);
				
				doRender(partialTicks);
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
	
	
	public Rectangle toRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
}
