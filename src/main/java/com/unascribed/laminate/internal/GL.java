package com.unascribed.laminate.internal;

import java.nio.FloatBuffer;

public final class GL {

	public static void pushAttrib() {
		LaminateInternal.gl().pushAttrib();
	}

	public static void popAttrib() {
		LaminateInternal.gl().popAttrib();
	}

	public static void disableAlpha() {
		LaminateInternal.gl().disableAlpha();
	}

	public static void enableAlpha() {
		LaminateInternal.gl().enableAlpha();
	}

	public static void alphaFunc(int func, float ref) {
		LaminateInternal.gl().alphaFunc(func, ref);
	}

	public static void enableLighting() {
		LaminateInternal.gl().enableLighting();
	}

	public static void disableLighting() {
		LaminateInternal.gl().disableLighting();
	}

	public static void enableLight(int light) {
		LaminateInternal.gl().enableLight(light);
	}

	public static void disableLight(int light) {
		LaminateInternal.gl().disableLight(light);
	}

	public static void enableColorMaterial() {
		LaminateInternal.gl().enableColorMaterial();
	}

	public static void disableColorMaterial() {
		LaminateInternal.gl().disableColorMaterial();
	}

	public static void colorMaterial(int face, int mode) {
		LaminateInternal.gl().colorMaterial(face, mode);
	}

	public static void disableDepth() {
		LaminateInternal.gl().disableDepth();
	}

	public static void enableDepth() {
		LaminateInternal.gl().enableDepth();
	}

	public static void depthFunc(int depthFunc) {
		LaminateInternal.gl().depthFunc(depthFunc);
	}

	public static void depthMask(boolean flagIn) {
		LaminateInternal.gl().depthMask(flagIn);
	}

	public static void disableBlend() {
		LaminateInternal.gl().disableBlend();
	}

	public static void enableBlend() {
		LaminateInternal.gl().enableBlend();
	}

	public static void blendFunc(int srcFactor, int dstFactor) {
		LaminateInternal.gl().blendFunc(srcFactor, dstFactor);
	}

	public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
		LaminateInternal.gl().tryBlendFuncSeparate(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
	}

	public static void enableFog() {
		LaminateInternal.gl().enableFog();
	}

	public static void disableFog() {
		LaminateInternal.gl().disableFog();
	}

	public static void setFog(int param) {
		LaminateInternal.gl().setFog(param);
	}

	public static void setFogDensity(float param) {
		LaminateInternal.gl().setFogDensity(param);
	}

	public static void setFogStart(float param) {
		LaminateInternal.gl().setFogStart(param);
	}

	public static void setFogEnd(float param) {
		LaminateInternal.gl().setFogEnd(param);
	}

	public static void enableCull() {
		LaminateInternal.gl().enableCull();
	}

	public static void disableCull() {
		LaminateInternal.gl().disableCull();
	}

	public static void cullFace(int mode) {
		LaminateInternal.gl().cullFace(mode);
	}

	public static void enablePolygonOffset() {
		LaminateInternal.gl().enablePolygonOffset();
	}

	public static void disablePolygonOffset() {
		LaminateInternal.gl().disablePolygonOffset();
	}

	public static void doPolygonOffset(float factor, float units) {
		LaminateInternal.gl().doPolygonOffset(factor, units);
	}

	public static void enableColorLogic() {
		LaminateInternal.gl().enableColorLogic();
	}

	public static void disableColorLogic() {
		LaminateInternal.gl().disableColorLogic();
	}

	public static void colorLogicOp(int opcode) {
		LaminateInternal.gl().colorLogicOp(opcode);
	}

	public static void setActiveTexture(int texture) {
		LaminateInternal.gl().setActiveTexture(texture);
	}

	public static void enableTexture2D() {
		LaminateInternal.gl().enableTexture2D();
	}

	public static void disableTexture2D() {
		LaminateInternal.gl().disableTexture2D();
	}

	public static void deleteTexture(int texture) {
		LaminateInternal.gl().deleteTexture(texture);
	}

	public static void bindTexture(int texture) {
		LaminateInternal.gl().bindTexture(texture);
	}

	public static void enableNormalize() {
		LaminateInternal.gl().enableNormalize();
	}

	public static void disableNormalize() {
		LaminateInternal.gl().disableNormalize();
	}

	public static void shadeModel(int mode) {
		LaminateInternal.gl().shadeModel(mode);
	}

	public static void enableRescaleNormal() {
		LaminateInternal.gl().enableRescaleNormal();
	}

	public static void disableRescaleNormal() {
		LaminateInternal.gl().disableRescaleNormal();
	}

	public static void viewport(int x, int y, int width, int height) {
		LaminateInternal.gl().viewport(x, y, width, height);
	}

	public static void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		LaminateInternal.gl().colorMask(red, green, blue, alpha);
	}

	public static void clearDepth(double depth) {
		LaminateInternal.gl().clearDepth(depth);
	}

	public static void clearColor(float red, float green, float blue, float alpha) {
		LaminateInternal.gl().clearColor(red, green, blue, alpha);
	}

	public static void clear(int mask) {
		LaminateInternal.gl().clear(mask);
	}

	public static void matrixMode(int mode) {
		LaminateInternal.gl().matrixMode(mode);
	}

	public static void loadIdentity() {
		LaminateInternal.gl().loadIdentity();
	}

	public static void pushMatrix() {
		LaminateInternal.gl().pushMatrix();
	}

	public static void popMatrix() {
		LaminateInternal.gl().popMatrix();
	}

	public static void getFloat(int pname, FloatBuffer params) {
		LaminateInternal.gl().getFloat(pname, params);
	}

	public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
		LaminateInternal.gl().ortho(left, right, bottom, top, zNear, zFar);
	}

	public static void rotate(float angle, float x, float y, float z) {
		LaminateInternal.gl().rotate(angle, x, y, z);
	}

	public static void scale(float x, float y, float z) {
		LaminateInternal.gl().scale(x, y, z);
	}

	public static void scale(double x, double y, double z) {
		LaminateInternal.gl().scale(x, y, z);
	}

	public static void translate(float x, float y, float z) {
		LaminateInternal.gl().translate(x, y, z);
	}

	public static void translate(double x, double y, double z) {
		LaminateInternal.gl().translate(x, y, z);
	}

	public static void multMatrix(FloatBuffer matrix) {
		LaminateInternal.gl().multMatrix(matrix);
	}

	public static void color(float colorRed, float colorGreen, float colorBlue, float colorAlpha) {
		LaminateInternal.gl().color(colorRed, colorGreen, colorBlue, colorAlpha);
	}

	public static void color(float colorRed, float colorGreen, float colorBlue) {
		LaminateInternal.gl().color(colorRed, colorGreen, colorBlue);
	}

	public static void resetColor() {
		LaminateInternal.gl().resetColor();
	}

	public static void callList(int list) {
		LaminateInternal.gl().callList(list);
	}

	public static void pushScissor() {
		SmartScissor.push();
	}

	public static void popScissor() {
		SmartScissor.pop();
	}

	public static void setScissorFromComponentCoordinates(int x, int y, int width, int height) {
		SmartScissor.setFromComponentCoordinates(x, y, width, height);
	}
	
	public static void setScissor(int x, int y, int width, int height) {
		SmartScissor.set(x, y, width, height);
	}
	
	public static void translateScissor(int x, int y) {
		SmartScissor.translate(x, y);
	}
	
	private GL() {}

}
