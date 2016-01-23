package com.unascribed.laminate.internal.gl;

import java.nio.FloatBuffer;

import net.minecraft.client.renderer.GlStateManager;

public class StateManagerGLAccess implements GLAccess {

	@Override
	public void pushAttrib() {
		GlStateManager.pushAttrib();
	}

	@Override
	public void popAttrib() {
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
