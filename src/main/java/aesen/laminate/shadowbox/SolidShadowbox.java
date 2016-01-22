package aesen.laminate.shadowbox;

import aesen.laminate.Laminate;
import net.minecraft.client.gui.Gui;

/*
 * A shadowbox consisting of one solid color.
 */
public class SolidShadowbox extends Shadowbox {
	private int color;
	
	/**
	 * Creates a SolidShadowbox of the given packed 24-bit color.
	 * @param color a packed color, such as 0xFF0000
	 */
	public SolidShadowbox(int color) {
		this.color = color;
	}
	
	/**
	 * Creates a SolidShadowbox with the given red, green, and blue values.
	 * @param r the red value
	 * @param g the green value
	 * @param b the blue value
	 */
	public SolidShadowbox(float r, float g, float b) {
		int packed = 0;
		packed |= (((int)(r*255))&0xFF)<<16;
		packed |= (((int)(g*255))&0xFF)<<8;
		packed |= (((int)(b*255))&0xFF);
		this.color = packed;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	@Override
	public void render(float partialTicks) {
		Gui.drawRect(0, 0, Laminate.getWidth(), Laminate.getHeight(), color | 0xFF000000);
	}

}
