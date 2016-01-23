package aesen.laminate.shadowbox;

import org.lwjgl.opengl.GL11;

import com.unascribed.laminate.internal.LaminateInternal;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess;

import aesen.laminate.Laminate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * A shadowbox that is just a tiled texture covering the entire background.
 *
 */
public class TextureShadowbox extends Shadowbox {
	private ResourceLocation texture;
	
	/**
	 * Creates a TextureShadowbox with the default gui background texture,
	 * which is dirt on the default resource pack.
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
		
		LaminateInternal.gl().disableLighting();
		LaminateInternal.gl().disableFog();
		TessellatorAccess tess = LaminateInternal.tess();
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		LaminateInternal.gl().color(1.0F, 1.0F, 1.0F, 1.0F);
		tess.begin(GL11.GL_QUADS, TessellatorAccess.Format.POSITION_TEX_COLOR);
		tess.pos(0, height, 0).tex(0, (height / 32f)).color(64, 64, 64, 255).endVertex();
		tess.pos(width, height, 0).tex((width / 32f), (height / 32f)).color(64, 64, 64, 255).endVertex();
		tess.pos(width, 0, 0).tex((width / 32f), 0).color(64, 64, 64, 255).endVertex();
		tess.pos(0, 0, 0).tex(0, 0).color(64, 64, 64, 255).endVertex();
		tess.draw();
	}
}
