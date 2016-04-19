package aesen.laminate.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Label extends Component {
	private String text = "";
	private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
	private int color = 0xFFFFFFFF;
	
	@Override
	protected void doRender(float partialTicks) {
		fontRenderer.drawStringWithShadow(text, 0, 0, color);
	}
	
	
	private void updateSize() {
		setWidth(fontRenderer.getStringWidth(text));
		setHeight(fontRenderer.FONT_HEIGHT);
	}
	
	public void setText(String text) {
		this.text = text;
		updateSize();
	}
	
	public void setFontRenderer(FontRenderer fontRenderer) {
		this.fontRenderer = fontRenderer;
		updateSize();
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	
	public String getText() {
		return text;
	}
	
	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
	
	public int getColor() {
		return color;
	}
}
