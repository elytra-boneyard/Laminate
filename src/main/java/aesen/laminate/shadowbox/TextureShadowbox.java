package aesen.laminate.shadowbox;

import aesen.laminate.Laminate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/**
 * A shadowbox that is just a tiled texture covering the entire background.
 *
 */
public class TextureShadowbox extends Shadowbox {
	private ResourceLocation texture;
	
	/**
	 * Creates a TextureShadowbox with the default gui background texture.
	 */
	public TextureShadowbox() {
		this(Gui.optionsBackground);
	}
	
	/**
	 * Creates a TextureShadowbox with the given background texture.
	 * @param texture the texture to use
	 */
	public TextureShadowbox(ResourceLocation texture) {
		this.texture = texture;
	}

	public void setTexture(ResourceLocation texture) {
		this.texture = texture;
	}
	
	public ResourceLocation getTexture() {
		return texture;
	}
	
	@Override
	public void render(float partialTicks) {
		int width = Laminate.getWidth();
		int height = Laminate.getHeight();
		
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer wr = tessellator.getWorldRenderer();
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		wr.pos(0, height, 0).tex(0, (height / 32f)).color(64, 64, 64, 255).endVertex();
		wr.pos(width, height, 0).tex((width / 32f), (height / 32f)).color(64, 64, 64, 255).endVertex();
		wr.pos(width, 0, 0).tex((width / 32f), 0).color(64, 64, 64, 255).endVertex();
		wr.pos(0, 0, 0).tex(0, 0).color(64, 64, 64, 255).endVertex();
		tessellator.draw();
	}
}
