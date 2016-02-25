package aesen.laminate.shadowbox;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import com.unascribed.laminate.internal.GL;
import com.unascribed.laminate.internal.LaminateInternal;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess;

import aesen.laminate.Laminate;
import aesen.laminate.Rendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

/**
 * A shadowbox imitating the panorama in the background of the main menu.
 *
 */
public class PanoramaShadowbox extends Shadowbox {
	private boolean useGlobalPanoramaTimer = true;
	private int panoramaTimer;
	
	private ResourceLocation[] panoramaPaths;
	
	public PanoramaShadowbox() {
		panoramaPaths = titlePanoramaPaths;
		init();
	}
	
	/**
	 * If set to true, this shadowbox will share it's timer with other
	 * PanoramaShadowboxes that also have this enabled. This allows
	 * transitions between two Screens, both using PanoramaShadowboxes,
	 * to be seamless.
	 * <p>
	 * The global timer is synced with that of the main menu, meaning
	 * it will also be a seamless transition from the main menu.
	 * <p>
	 * Defaults to true.
	 * 
	 * @param useGlobalPanoramaTimer whether or not to use the global panorama timer
	 */
	public void setUseGlobalPanoramaTimer(boolean useGlobalPanoramaTimer) {
		this.useGlobalPanoramaTimer = useGlobalPanoramaTimer;
	}
	
	public boolean useGlobalPanoramaTimer() {
		return useGlobalPanoramaTimer;
	}
	
	public void setPanoramaPaths(ResourceLocation... panoramaPaths) {
		if (panoramaPaths == null || panoramaPaths.length != 6) {
			throw new IllegalArgumentException("Panorama paths must not be null and must have exactly 6 entries");
		}
		this.panoramaPaths = panoramaPaths.clone();
	}
	
	public ResourceLocation[] getPanoramaPaths() {
		return Arrays.copyOf(panoramaPaths, panoramaPaths.length);
	}
	
	@Override
	public void render(float partialTicks) {
		GL.disableAlpha();
		renderSkybox(partialTicks);
		GL.enableAlpha();
		int width = Laminate.getWidth();
		int height = Laminate.getHeight();
		Rendering.drawGradientRect(0, 0, width, height, 0x80FFFFFF, 0x00FFFFFF);
		Rendering.drawGradientRect(0, 0, width, height, 0x00000000, 0x80000000);
	}
	
	@Override
	public void tick() {
		if (!useGlobalPanoramaTimer) {
			panoramaTimer++;
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		viewportTexture.deleteGlTexture();
		super.finalize();
	}

	
	// Below code shamelessly stolen from GuiMainMenu
	
	private DynamicTexture viewportTexture;
	private ResourceLocation backgroundTexture;
	private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] {
			new ResourceLocation("textures/gui/title/background/panorama_0.png"),
			new ResourceLocation("textures/gui/title/background/panorama_1.png"),
			new ResourceLocation("textures/gui/title/background/panorama_2.png"),
			new ResourceLocation("textures/gui/title/background/panorama_3.png"),
			new ResourceLocation("textures/gui/title/background/panorama_4.png"),
			new ResourceLocation("textures/gui/title/background/panorama_5.png")};
	
	private void init() {
		this.viewportTexture = new DynamicTexture(256, 256);
		this.backgroundTexture = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
	}
	
	/**
	 * Draws the main menu panorama
	 */
	private void drawPanorama(float partialTicks) {
		int panoramaTimer = (useGlobalPanoramaTimer ? LaminateInternal.globalPanoramaTimer : this.panoramaTimer);
		
		TessellatorAccess tess = LaminateInternal.tess();
		GL.matrixMode(5889);
		GL.pushMatrix();
		GL.loadIdentity();
		Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
		GL.matrixMode(5888);
		GL.pushMatrix();
		GL.loadIdentity();
		GL.color(1.0F, 1.0F, 1.0F, 1.0F);
		GL.rotate(180.0F, 1.0F, 0.0F, 0.0F);
		GL.rotate(90.0F, 0.0F, 0.0F, 1.0F);
		GL.enableBlend();
		GL.disableAlpha();
		GL.disableCull();
		GL.depthMask(false);
		GL.tryBlendFuncSeparate(770, 771, 1, 0);
		int i = 8;

		for (int j = 0; j < i * i; ++j) {
			GL.pushMatrix();
			float f = ((float) (j % i) / (float) i - 0.5F) / 64.0F;
			float f1 = ((float) (j / i) / (float) i - 0.5F) / 64.0F;
			float f2 = 0.0F;
			GL.translate(f, f1, f2);
			GL.rotate(MathHelper.sin((panoramaTimer + partialTicks) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
			GL.rotate(-(panoramaTimer + partialTicks) * 0.1F, 0.0F, 1.0F, 0.0F);

			for (int k = 0; k < 6; ++k) {
				GL.pushMatrix();

				if (k == 1) {
					GL.rotate(90.0F, 0.0F, 1.0F, 0.0F);
				}

				if (k == 2) {
					GL.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				}

				if (k == 3) {
					GL.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
				}

				if (k == 4) {
					GL.rotate(90.0F, 1.0F, 0.0F, 0.0F);
				}

				if (k == 5) {
					GL.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
				}

				Minecraft.getMinecraft().getTextureManager().bindTexture(panoramaPaths[k]);
				tess.begin(GL11.GL_QUADS, TessellatorAccess.Format.POSITION_TEX_COLOR);
				int l = 255 / (j + 1);
				tess.pos(-1.0D, -1.0D, 1.0D).tex(0.0D, 0.0D).color(255, 255, 255, l).endVertex();
				tess.pos(1.0D, -1.0D, 1.0D).tex(1.0D, 0.0D).color(255, 255, 255, l).endVertex();
				tess.pos(1.0D, 1.0D, 1.0D).tex(1.0D, 1.0D).color(255, 255, 255, l).endVertex();
				tess.pos(-1.0D, 1.0D, 1.0D).tex(0.0D, 1.0D).color(255, 255, 255, l).endVertex();
				tess.draw();
				GL.popMatrix();
			}

			GL.popMatrix();
			GL.colorMask(true, true, true, false);
		}

		GL.colorMask(true, true, true, true);
		GL.matrixMode(5889);
		GL.popMatrix();
		GL.matrixMode(5888);
		GL.popMatrix();
		GL.depthMask(true);
		GL.enableCull();
		GL.enableDepth();
	}

	/**
	 * Rotate and blurs the skybox view in the main menu
	 */
	private void rotateAndBlurSkybox() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.backgroundTexture);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
		GL.enableBlend();
		GL.tryBlendFuncSeparate(770, 771, 1, 0);
		GL.colorMask(true, true, true, false);
		TessellatorAccess tess = LaminateInternal.tess();
		tess.begin(GL11.GL_QUADS, TessellatorAccess.Format.POSITION_TEX_COLOR);
		GL.disableAlpha();
		int i = 3;

		for (int j = 0; j < i; ++j) {
			float f = 1.0F / (j + 1);
			int k = Laminate.getWidth();
			int l = Laminate.getHeight();
			float f1 = (j - i / 2) / 256.0F;
			tess.pos(k, l, 0).tex(0.0F + f1, 1.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
			tess.pos(k, 0.0D, 0).tex(1.0F + f1, 1.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
			tess.pos(0.0D, 0.0D, 0).tex(1.0F + f1, 0.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
			tess.pos(0.0D, l, 0).tex(0.0F + f1, 0.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
		}

		tess.draw();
		GL.enableAlpha();
		GL.colorMask(true, true, true, true);
	}

	/**
	 * Renders the skybox in the main menu
	 */
	private void renderSkybox(float partialTicks) {
		Minecraft.getMinecraft().getFramebuffer().unbindFramebuffer();
		GL.viewport(0, 0, 256, 256);
		this.drawPanorama(partialTicks);
		this.rotateAndBlurSkybox();
		this.rotateAndBlurSkybox();
		this.rotateAndBlurSkybox();
		this.rotateAndBlurSkybox();
		this.rotateAndBlurSkybox();
		this.rotateAndBlurSkybox();
		this.rotateAndBlurSkybox();
		Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
		GL.viewport(0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		float f = Laminate.getWidth() > Laminate.getHeight() ? 120.0F / Laminate.getWidth() : 120.0F / Laminate.getHeight();
		float f1 = Laminate.getHeight() * f / 256.0F;
		float f2 = Laminate.getWidth() * f / 256.0F;
		int i = Laminate.getWidth();
		int j = Laminate.getHeight();
		TessellatorAccess tess = LaminateInternal.tess();
		tess.begin(GL11.GL_QUADS, TessellatorAccess.Format.POSITION_TEX_COLOR);
		tess.pos(0.0D, j, 0).tex(0.5F - f1, 0.5F + f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		tess.pos(i, j, 0).tex(0.5F - f1, 0.5F - f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		tess.pos(i, 0.0D, 0).tex(0.5F + f1, 0.5F - f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		tess.pos(0.0D, 0.0D, 0).tex(0.5F + f1, 0.5F + f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		tess.draw();
	}
}
