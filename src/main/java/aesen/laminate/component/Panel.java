package aesen.laminate.component;

import javax.annotation.Nullable;

import com.unascribed.laminate.internal.GL;
import aesen.laminate.Colors;
import aesen.laminate.Rendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * A basic container for other components, potentially with a border.
 */
public class Panel extends Component {
	private boolean showBorder = false;
	private int borderColor = 0xFFFFFFFF;
	
	private String title = null;
	private FontRenderer titleFontRenderer = Minecraft.getMinecraft().fontRendererObj;
	
	@Override
	protected void doRender(float partialTicks) {
		if (showBorder) {
			drawBorder(1, 1, getWidth()-1, getHeight()-1, Colors.shadow(borderColor));
			drawBorder(0, 0, getWidth()-1, getHeight()-1, borderColor);
		}
	}
	
	private void drawBorder(int x, int y, int width, int height, int color) {
		GL.color(color);
		// this would be slightly more efficient as a polygon, but GL_POLYGONS
		// is only good for convex polygons, and the border is a concave polygon
		if (title != null) {
			titleFontRenderer.drawString(title, x+6, y, color);
			y += 4;
			height -= 4;
			Rendering.drawRect(x+1, y, x+3, y+1, color);
			Rendering.drawRect(x+titleFontRenderer.getStringWidth(title)+8, y, x+width-1, y+1, color);
		} else {
			Rendering.drawRect(x, y, x+width, y+1, color);
		}
		Rendering.drawRect(x, y, x+1, y+height, color);
		Rendering.drawRect(x+1, y+height-1, x+width, y+height, color);
		Rendering.drawRect(x+width-1, y, x+width, y+height-1, color);
	}

	@Override
	public int getSafeZoneX() {
		return showBorder ? 6 : 0;
	}
	
	@Override
	public int getSafeZoneY() {
		return showBorder ? (title == null ? 6 : 13) : 0;
	}
	
	@Override
	public int getSafeZoneWidth() {
		return width - (showBorder ? 13 : 0);
	}
	
	@Override
	public int getSafeZoneHeight() {
		return height - (showBorder ? (title == null ? 13 : 20) : 0);
	}
	
	
	public void setShowBorder(boolean showBorder) {
		this.showBorder = showBorder;
	}
	
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}
	
	public void setBorderColor(float r, float g, float b) {
		setBorderColor(Colors.pack(r, g, b, 1));
	}
	
	/**
	 * If null, no title will be displayed.
	 */
	public void setTitle(@Nullable String title) {
		this.title = title;
	}
	
	public void setTitleFontRenderer(FontRenderer titleFontRenderer) {
		this.titleFontRenderer = titleFontRenderer;
	}
	
	
	
	public boolean getShowBorder() {
		return showBorder;
	}
	
	public int getBorderColor() {
		return borderColor;
	}
	
	
	@Nullable
	public String getTitle() {
		return title;
	}
	
	public FontRenderer getTitleFontRenderer() {
		return titleFontRenderer;
	}
	
}
