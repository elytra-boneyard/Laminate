package aesen.laminate;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class Rendering {
	private static class DummyGui extends Gui {
		@Override
		public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
			super.drawGradientRect(left, top, right, bottom, startColor, endColor);
		}
		@Override
		public void drawHorizontalLine(int startX, int endX, int y, int color) {
			super.drawHorizontalLine(startX, endX, y, color);
		}
		@Override
		public void drawVerticalLine(int x, int startY, int endY, int color) {
			super.drawVerticalLine(x, startY, endY, color);
		}
	}


	private static final DummyGui GUI = new DummyGui();
	
	
	public static void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
		GUI.drawCenteredString(fontRendererIn, text, x, y, color);
	}
	
	public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
		GUI.drawGradientRect(left, top, right, bottom, startColor, endColor);
	}
	
	public static void drawHorizontalLine(int startX, int endX, int y, int color) {
		GUI.drawHorizontalLine(startX, endX, y, color);
	}
	
	public static void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
		GUI.drawString(fontRendererIn, text, x, y, color);
	}
	
	public static void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV) {
		GUI.drawTexturedModalRect(xCoord, yCoord, minU, minV, maxU, maxV);
	}
	
	public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
		GUI.drawTexturedModalRect(x, y, textureX, textureY, width, height);
	}
	
	public static void drawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn, int heightIn) {
		GUI.drawTexturedModalRect(xCoord, yCoord, textureSprite, widthIn, heightIn);
	}
	
	public static void drawVerticalLine(int x, int startY, int endY, int color) {
		GUI.drawVerticalLine(x, startY, endY, color);
	}
	
	
	private Rendering() {}
}
