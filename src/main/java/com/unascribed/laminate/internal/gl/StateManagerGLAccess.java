package com.unascribed.laminate.internal.gl;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import net.minecraft.client.renderer.GlStateManager;

public class StateManagerGLAccess implements GLAccess {
	private static final int               ALPHA_BIT = 0b1;
	private static final int               BLEND_BIT = 0b10;
	private static final int      COLOR_MATERIAL_BIT = 0b100;
	private static final int           CULL_FACE_BIT = 0b1000;
	private static final int          DEPTH_TEST_BIT = 0b10000;
	private static final int                 FOG_BIT = 0b100000;
	private static final int              LIGHT0_BIT = 0b1000000;
	private static final int              LIGHT1_BIT = 0b10000000;
	private static final int              LIGHT2_BIT = 0b100000000;
	private static final int              LIGHT3_BIT = 0b1000000000;
	private static final int              LIGHT4_BIT = 0b10000000000;
	private static final int              LIGHT5_BIT = 0b100000000000;
	private static final int              LIGHT6_BIT = 0b1000000000000;
	private static final int              LIGHT7_BIT = 0b10000000000000;
	private static final int            LIGHTING_BIT = 0b100000000000000;
	private static final int      COLOR_LOGIC_OP_BIT = 0b1000000000000000;
	private static final int           NORMALIZE_BIT = 0b10000000000000000;
	private static final int POLYGON_OFFSET_FILL_BIT = 0b100000000000000000;
	private static final int POLYGON_OFFSET_LINE_BIT = 0b1000000000000000000;
	private static final int       TEXTURE_GEN_S_BIT = 0b10000000000000000000;
	private static final int       TEXTURE_GEN_T_BIT = 0b100000000000000000000;
	private static final int       TEXTURE_GEN_R_BIT = 0b1000000000000000000000;
	private static final int       TEXTURE_GEN_Q_BIT = 0b10000000000000000000000;
	private static final int        TEXTURE_2D_0_BIT = 0b100000000000000000000000;
	private static final int        TEXTURE_2D_1_BIT = 0b1000000000000000000000000;
	private static final int        TEXTURE_2D_2_BIT = 0b10000000000000000000000000;
	private static final int        TEXTURE_2D_3_BIT = 0b100000000000000000000000000;
	private static final int        TEXTURE_2D_4_BIT = 0b1000000000000000000000000000;
	private static final int        TEXTURE_2D_5_BIT = 0b10000000000000000000000000000;
	private static final int        TEXTURE_2D_6_BIT = 0b100000000000000000000000000000;
	private static final int        TEXTURE_2D_7_BIT = 0b1000000000000000000000000000000;
	private static final int              SMOOTH_BIT = 0b10000000000000000000000000000000;
	
	private TIntList attribStack = new TIntArrayList();
	private final boolean emulatePushAttrib;
	
	public StateManagerGLAccess(boolean emulatePushAttrib) {
		this.emulatePushAttrib = emulatePushAttrib;
	}
	
	@Override
	public void pushAttrib() {
		if (emulatePushAttrib) {
			// Minecraft's GlStateManager desyncs when pushAttrib and popAttrib are used
			
			// this is basically what the state manager should be doing, and hopefully it
			// will do this in a future version of Minecraft
			int state = 0;
			if (GlStateManager.alphaState.field_179208_a.currentState) state |= ALPHA_BIT;
			if (GlStateManager.blendState.field_179213_a.currentState) state |= BLEND_BIT;
			if (GlStateManager.colorMaterialState.field_179191_a.currentState) state |= COLOR_MATERIAL_BIT;
			if (GlStateManager.cullState.field_179054_a.currentState) state |= CULL_FACE_BIT;
			if (GlStateManager.depthState.depthTest.currentState) state |= DEPTH_TEST_BIT;
			if (GlStateManager.fogState.field_179049_a.currentState) state |= FOG_BIT;
			if (GlStateManager.lightState[0].currentState) state |= LIGHT0_BIT;
			if (GlStateManager.lightState[1].currentState) state |= LIGHT1_BIT;
			if (GlStateManager.lightState[2].currentState) state |= LIGHT2_BIT;
			if (GlStateManager.lightState[3].currentState) state |= LIGHT3_BIT;
			if (GlStateManager.lightState[4].currentState) state |= LIGHT4_BIT;
			if (GlStateManager.lightState[5].currentState) state |= LIGHT5_BIT;
			if (GlStateManager.lightState[6].currentState) state |= LIGHT6_BIT;
			if (GlStateManager.lightState[7].currentState) state |= LIGHT7_BIT;
			if (GlStateManager.lightingState.currentState) state |= LIGHTING_BIT;
			if (GlStateManager.colorLogicState.field_179197_a.currentState) state |= COLOR_LOGIC_OP_BIT;
			if (GlStateManager.normalizeState.currentState) state |= NORMALIZE_BIT;
			if (GlStateManager.polygonOffsetState.field_179044_a.currentState) state |= POLYGON_OFFSET_FILL_BIT;
			if (GlStateManager.polygonOffsetState.field_179042_b.currentState) state |= POLYGON_OFFSET_LINE_BIT;
			if (GlStateManager.texGenState.field_179064_a.field_179067_a.currentState) state |= TEXTURE_GEN_S_BIT;
			if (GlStateManager.texGenState.field_179062_b.field_179067_a.currentState) state |= TEXTURE_GEN_T_BIT;
			if (GlStateManager.texGenState.field_179063_c.field_179067_a.currentState) state |= TEXTURE_GEN_R_BIT;
			if (GlStateManager.texGenState.field_179061_d.field_179067_a.currentState) state |= TEXTURE_GEN_Q_BIT;
			if (GlStateManager.textureState[0].texture2DState.currentState) state |= TEXTURE_2D_0_BIT;
			if (GlStateManager.textureState[1].texture2DState.currentState) state |= TEXTURE_2D_1_BIT;
			if (GlStateManager.textureState[2].texture2DState.currentState) state |= TEXTURE_2D_2_BIT;
			if (GlStateManager.textureState[3].texture2DState.currentState) state |= TEXTURE_2D_3_BIT;
			if (GlStateManager.textureState[4].texture2DState.currentState) state |= TEXTURE_2D_4_BIT;
			if (GlStateManager.textureState[5].texture2DState.currentState) state |= TEXTURE_2D_5_BIT;
			if (GlStateManager.textureState[6].texture2DState.currentState) state |= TEXTURE_2D_6_BIT;
			if (GlStateManager.textureState[7].texture2DState.currentState) state |= TEXTURE_2D_7_BIT;
			if (GlStateManager.activeShadeModel == GL11.GL_SMOOTH) state |= SMOOTH_BIT;
			attribStack.add(state);
		}
		GlStateManager.pushAttrib();
	}

	@Override
	public void popAttrib() {
		if (emulatePushAttrib) {
			int state = attribStack.removeAt(attribStack.size()-1);
			GlStateManager.alphaState.field_179208_a.currentState = ((state & ALPHA_BIT) != 0);
			GlStateManager.blendState.field_179213_a.currentState = ((state & BLEND_BIT) != 0);
			GlStateManager.colorMaterialState.field_179191_a.currentState = ((state & COLOR_MATERIAL_BIT) != 0);
			GlStateManager.cullState.field_179054_a.currentState = ((state & CULL_FACE_BIT) != 0);
			GlStateManager.depthState.depthTest.currentState = ((state & DEPTH_TEST_BIT) != 0);
			GlStateManager.fogState.field_179049_a.currentState = ((state & FOG_BIT) != 0);
			GlStateManager.lightState[0].currentState = ((state & LIGHT0_BIT) != 0);
			GlStateManager.lightState[1].currentState = ((state & LIGHT1_BIT) != 0);
			GlStateManager.lightState[2].currentState = ((state & LIGHT2_BIT) != 0);
			GlStateManager.lightState[3].currentState = ((state & LIGHT3_BIT) != 0);
			GlStateManager.lightState[4].currentState = ((state & LIGHT4_BIT) != 0);
			GlStateManager.lightState[5].currentState = ((state & LIGHT5_BIT) != 0);
			GlStateManager.lightState[6].currentState = ((state & LIGHT6_BIT) != 0);
			GlStateManager.lightState[7].currentState = ((state & LIGHT7_BIT) != 0);
			GlStateManager.lightingState.currentState = ((state & LIGHTING_BIT) != 0);
			GlStateManager.colorLogicState.field_179197_a.currentState = ((state & COLOR_LOGIC_OP_BIT) != 0);
			GlStateManager.normalizeState.currentState = ((state & NORMALIZE_BIT) != 0);
			GlStateManager.polygonOffsetState.field_179044_a.currentState = ((state & POLYGON_OFFSET_FILL_BIT) != 0);
			GlStateManager.polygonOffsetState.field_179042_b.currentState = ((state & POLYGON_OFFSET_LINE_BIT) != 0);
			GlStateManager.texGenState.field_179064_a.field_179067_a.currentState = ((state & TEXTURE_GEN_S_BIT) != 0);
			GlStateManager.texGenState.field_179062_b.field_179067_a.currentState = ((state & TEXTURE_GEN_T_BIT) != 0);
			GlStateManager.texGenState.field_179063_c.field_179067_a.currentState = ((state & TEXTURE_GEN_R_BIT) != 0);
			GlStateManager.texGenState.field_179061_d.field_179067_a.currentState = ((state & TEXTURE_GEN_Q_BIT) != 0);
			GlStateManager.textureState[0].texture2DState.currentState = ((state & TEXTURE_2D_0_BIT) != 0);
			GlStateManager.textureState[1].texture2DState.currentState = ((state & TEXTURE_2D_1_BIT) != 0);
			GlStateManager.textureState[2].texture2DState.currentState = ((state & TEXTURE_2D_2_BIT) != 0);
			GlStateManager.textureState[3].texture2DState.currentState = ((state & TEXTURE_2D_3_BIT) != 0);
			GlStateManager.textureState[4].texture2DState.currentState = ((state & TEXTURE_2D_4_BIT) != 0);
			GlStateManager.textureState[5].texture2DState.currentState = ((state & TEXTURE_2D_5_BIT) != 0);
			GlStateManager.textureState[6].texture2DState.currentState = ((state & TEXTURE_2D_6_BIT) != 0);
			GlStateManager.textureState[7].texture2DState.currentState = ((state & TEXTURE_2D_7_BIT) != 0);
			if ((state & SMOOTH_BIT) != 0) {
				GlStateManager.activeShadeModel = GL11.GL_SMOOTH;
			} else {
				GlStateManager.activeShadeModel = GL11.GL_FLAT;
			}
		}
		GlStateManager.popAttrib();
	}

	@Override
	public void disableAlpha() {
		GlStateManager.disableAlpha();

	}

	@Override
	public void enableAlpha() {
		GlStateManager.enableAlpha();

	}

	@Override
	public void alphaFunc(int func, float ref) {
		GlStateManager.alphaFunc(func, ref);

	}

	@Override
	public void enableLighting() {
		GlStateManager.enableLighting();

	}

	@Override
	public void disableLighting() {
		GlStateManager.disableLighting();

	}

	@Override
	public void enableLight(int light) {
		GlStateManager.enableLight(light);

	}

	@Override
	public void disableLight(int light) {
		GlStateManager.disableLight(light);

	}

	@Override
	public void enableColorMaterial() {
		GlStateManager.enableColorMaterial();

	}

	@Override
	public void disableColorMaterial() {
		GlStateManager.disableColorMaterial();

	}

	@Override
	public void colorMaterial(int face, int mode) {
		GlStateManager.colorMaterial(face, mode);

	}

	@Override
	public void disableDepth() {
		GlStateManager.disableDepth();

	}

	@Override
	public void enableDepth() {
		GlStateManager.enableDepth();

	}

	@Override
	public void depthFunc(int depthFunc) {
		GlStateManager.depthFunc(depthFunc);

	}

	@Override
	public void depthMask(boolean flagIn) {
		GlStateManager.depthMask(flagIn);

	}

	@Override
	public void disableBlend() {
		GlStateManager.disableBlend();

	}

	@Override
	public void enableBlend() {
		GlStateManager.enableBlend();

	}

	@Override
	public void blendFunc(int srcFactor, int dstFactor) {
		GlStateManager.blendFunc(srcFactor, dstFactor);

	}

	@Override
	public void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
		GlStateManager.tryBlendFuncSeparate(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);

	}

	@Override
	public void enableFog() {
		GlStateManager.enableFog();

	}

	@Override
	public void disableFog() {
		GlStateManager.disableFog();

	}

	@Override
	public void setFog(int param) {
		GlStateManager.setFog(param);

	}

	@Override
	public void setFogDensity(float param) {
		GlStateManager.setFogDensity(param);

	}

	@Override
	public void setFogStart(float param) {
		GlStateManager.setFogStart(param);

	}

	@Override
	public void setFogEnd(float param) {
		GlStateManager.setFogEnd(param);

	}

	@Override
	public void enableCull() {
		GlStateManager.enableCull();

	}

	@Override
	public void disableCull() {
		GlStateManager.disableCull();

	}

	@Override
	public void cullFace(int mode) {
		GlStateManager.cullFace(mode);

	}

	@Override
	public void enablePolygonOffset() {
		GlStateManager.enablePolygonOffset();

	}

	@Override
	public void disablePolygonOffset() {
		GlStateManager.disablePolygonOffset();

	}

	@Override
	public void doPolygonOffset(float factor, float units) {
		GlStateManager.doPolygonOffset(factor, units);

	}

	@Override
	public void enableColorLogic() {
		GlStateManager.enableColorLogic();

	}

	@Override
	public void disableColorLogic() {
		GlStateManager.disableColorLogic();

	}

	@Override
	public void colorLogicOp(int opcode) {
		GlStateManager.colorLogicOp(opcode);

	}

	@Override
	public void setActiveTexture(int texture) {
		GlStateManager.setActiveTexture(texture);

	}

	@Override
	public void enableTexture2D() {
		GlStateManager.enableTexture2D();

	}

	@Override
	public void disableTexture2D() {
		GlStateManager.disableTexture2D();

	}

	@Override
	public void deleteTexture(int texture) {
		GlStateManager.deleteTexture(texture);

	}

	@Override
	public void bindTexture(int texture) {
		GlStateManager.bindTexture(texture);

	}

	@Override
	public void enableNormalize() {
		GlStateManager.enableNormalize();

	}

	@Override
	public void disableNormalize() {
		GlStateManager.disableNormalize();

	}

	@Override
	public void shadeModel(int mode) {
		GlStateManager.shadeModel(mode);

	}

	@Override
	public void enableRescaleNormal() {
		GlStateManager.enableRescaleNormal();

	}

	@Override
	public void disableRescaleNormal() {
		GlStateManager.disableRescaleNormal();

	}

	@Override
	public void viewport(int x, int y, int width, int height) {
		GlStateManager.viewport(x, y, width, height);

	}

	@Override
	public void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GlStateManager.colorMask(red, green, blue, alpha);

	}

	@Override
	public void clearDepth(double depth) {
		GlStateManager.clearDepth(depth);

	}

	@Override
	public void clearColor(float red, float green, float blue, float alpha) {
		GlStateManager.clearColor(red, green, blue, alpha);

	}

	@Override
	public void clear(int mask) {
		GlStateManager.clear(mask);

	}

	@Override
	public void matrixMode(int mode) {
		GlStateManager.matrixMode(mode);

	}

	@Override
	public void loadIdentity() {
		GlStateManager.loadIdentity();

	}

	@Override
	public void pushMatrix() {
		GlStateManager.pushMatrix();

	}

	@Override
	public void popMatrix() {
		GlStateManager.popMatrix();

	}

	@Override
	public void getFloat(int pname, FloatBuffer params) {
		GlStateManager.getFloat(pname, params);

	}

	@Override
	public void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
		GlStateManager.ortho(left, right, bottom, top, zNear, zFar);

	}

	@Override
	public void rotate(float angle, float x, float y, float z) {
		GlStateManager.rotate(angle, x, y, z);

	}

	@Override
	public void scale(float x, float y, float z) {
		GlStateManager.scale(x, y, z);

	}

	@Override
	public void scale(double x, double y, double z) {
		GlStateManager.scale(x, y, z);

	}

	@Override
	public void translate(float x, float y, float z) {
		GlStateManager.translate(x, y, z);

	}

	@Override
	public void translate(double x, double y, double z) {
		GlStateManager.translate(x, y, z);

	}

	@Override
	public void multMatrix(FloatBuffer matrix) {
		GlStateManager.multMatrix(matrix);

	}

	@Override
	public void color(float colorRed, float colorGreen, float colorBlue, float colorAlpha) {
		GlStateManager.color(colorRed, colorGreen, colorBlue, colorAlpha);

	}

	@Override
	public void color(float colorRed, float colorGreen, float colorBlue) {
		GlStateManager.color(colorRed, colorGreen, colorBlue);

	}

	@Override
	public void resetColor() {
		GlStateManager.resetColor();

	}

	@Override
	public void callList(int list) {
		GlStateManager.callList(list);

	}

}
