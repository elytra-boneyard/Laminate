package aesen.laminate.shadowbox;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.unascribed.laminate.internal.GL;
import com.unascribed.laminate.internal.LaminateInternal;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess;

import aesen.laminate.Laminate;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * A shadowbox that looks like the starfield in an end portal.
 * Based on GuiEndPortalRenderer from HardcoreEnderExpansion.
 *
 */
public class EndShadowbox extends Shadowbox {

	private static final ResourceLocation texPortalSky = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation texPortal = new ResourceLocation("textures/entity/end_portal.png");
	private static final Random consistentRandom = new Random(31100L);

	public int portalTranslation;
	public int prevPortalTranslation;

	public EndShadowbox() {
		this.prevPortalTranslation = this.portalTranslation = ((int) (Math.random() * 10000));
	}

	private void update(int speed) {
		prevPortalTranslation = portalTranslation;
		portalTranslation += speed;
	}

	private void draw(float x, float y, float portalScale, float partialTickTime) {
		int width = Laminate.getWidth();
		int height = Laminate.getHeight();

		float div = (float) width / height;

		GL.disableLighting();
		consistentRandom.setSeed(31100L);

		for (int layer = 0; layer < 16; ++layer) {
			float revLayer = (16 - layer);
			float scale = 0.09625F;
			float colorMultiplier = 1F / (revLayer + 1F);
			float layerMp = 1F + (layer * 0.4F);

			if (layer == 0) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(texPortalSky);
				colorMultiplier = 0.1F;
				scale = 1.125F;
				GL.enableBlend();
				GL.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}

			if (layer >= 1) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(texPortal);

				if (layer == 1) {
					GL.enableBlend();
					GL.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
					scale = 0.2F;
				}
			}

			GL.matrixMode(GL11.GL_TEXTURE);
			GL.pushMatrix();
			GL.loadIdentity();

			GL.translate(0F, layerMp * (prevPortalTranslation + (portalTranslation - prevPortalTranslation) * partialTickTime) * 0.00002F, 0F);
			GL.scale(scale, scale, 1F);
			GL.scale(1F + revLayer * 0.15F, 1F + revLayer * 0.15F, 1F);
			GL.translate(0.5F, 0.5F, 0F);
			GL.rotate((layer * layer * 4321 + layer * 9) * 4F + 180F, 0F, 0F, 1F);

			GL.translate(x * 0.0025F * layerMp, y * 0.0025F * layerMp, 0F);
			GL.translate(0.5F * div, 0.5F, 0F);
			GL.scale(4F * portalScale, 4F * portalScale, 1F);
			GL.translate(-0.5F * div, -0.5F, 0F);

			GL.scale(div, 1F, 1F);

			TessellatorAccess tess = LaminateInternal.tess();
			tess.begin(GL11.GL_QUADS, TessellatorAccess.Format.POSITION_TEX_COLOR);

			float red = consistentRandom.nextFloat() * 0.5F + 0.1F;
			float green = consistentRandom.nextFloat() * 0.5F + 0.4F;
			float blue = consistentRandom.nextFloat() * 0.5F + 0.5F;
			if (layer == 0) {
				red = green = blue = 1F;
			}

			float r = red * colorMultiplier;
			float g = green * colorMultiplier;
			float b = blue * colorMultiplier;

			tess.pos(0, height, 0D).tex(0D, 1D).color(r, g, b, 1).endVertex();
			tess.pos(width, height, 0D).tex(1D, 1D).color(r, g, b, 1).endVertex();
			tess.pos(width, 0, 0D).tex(1D, 0D).color(r, g, b, 1).endVertex();
			tess.pos(0, 0, 0D).tex(0D, 0D).color(r, g, b, 1).endVertex();
			tess.draw();
			GL.popMatrix();
			GL.matrixMode(GL11.GL_MODELVIEW);
		}

		GL.disableBlend();
	}

	private float scale = 1;
	private int speed = 1;

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public float getScale() {
		return scale;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public void tick() {
		update(speed);
	}

	@Override
	public void render(float partialTicks) {
		draw(0, 0, scale, partialTicks);
	}

}
