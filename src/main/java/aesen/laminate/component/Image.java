package aesen.laminate.component;

import org.lwjgl.opengl.GL11;

import com.unascribed.laminate.internal.GL;
import com.unascribed.laminate.internal.LaminateInternal;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess.Format;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Image extends Component {
	private ResourceLocation src;
	private float minU = 0;
	private float minV = 0;
	private float maxU = 1;
	private float maxV = 1;
	
	@Override
	protected void doRender(float partialTicks) {
		GL.enableBlend();
		GL.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA,
				GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Minecraft.getMinecraft().getTextureManager().bindTexture(src);
		GL.color(1, 1, 1);
		TessellatorAccess tess = LaminateInternal.tess();
		tess.begin(GL11.GL_QUADS, Format.POSITION_TEX);
		tess.pos(0    , height, 0).tex(minU, maxV).endVertex();
		tess.pos(width, height, 0).tex(maxU, maxV).endVertex();
		tess.pos(width, 0     , 0).tex(maxU, minV).endVertex();
		tess.pos(0    , 0     , 0).tex(minU, minV).endVertex();
		tess.draw();
		GL.disableBlend();
	}
	
	
	public void setSource(ResourceLocation src) {
		this.src = src;
	}
	
	public void setUV(float minU, float minV, float maxU, float maxV) {
		setMinU(minU);
		setMaxU(maxU);
		setMinV(minV);
		setMaxV(maxV);
	}
	
	public void setMinU(float minU) {
		this.minU = minU;
	}
	
	public void setMaxU(float maxU) {
		this.maxU = maxU;
	}
	
	public void setMinV(float minV) {
		this.minV = minV;
	}
	
	public void setMaxV(float maxV) {
		this.maxV = maxV;
	}
	
	
	public ResourceLocation getSource() {
		return src;
	}
	
	public float getMinU() {
		return minU;
	}
	
	public float getMaxU() {
		return maxU;
	}
	
	public float getMinV() {
		return minV;
	}
	
	public float getMaxV() {
		return maxV;
	}
	

}
