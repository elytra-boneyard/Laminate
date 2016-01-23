package com.unascribed.laminate.internal.gl;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.OpenGlHelper;

public class DirectGLAccess implements GLAccess {

	@Override
	public void pushAttrib() {
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_LIGHTING_BIT);
	}

	@Override
	public void popAttrib() {
		GL11.glPopAttrib();
	}

	@Override
	public void disableAlpha() {
		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}

	@Override
	public void enableAlpha() {
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	@Override
	public void alphaFunc(int func, float ref) {
		GL11.glAlphaFunc(func, ref);
	}

	@Override
	public void enableLighting() {
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	@Override
	public void disableLighting() {
		GL11.glDisable(GL11.GL_LIGHTING);
	}

	@Override
	public void enableLight(int light) {
		GL11.glEnable(GL11.GL_LIGHT0+light); // yes, this is totally valid and is encouraged in the GL docs
	}

	@Override
	public void disableLight(int light) {
		GL11.glDisable(GL11.GL_LIGHT0+light);
	}

	@Override
	public void enableColorMaterial() {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	}

	@Override
	public void disableColorMaterial() {
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	@Override
	public void colorMaterial(int face, int mode) {
		GL11.glColorMaterial(face, mode);
	}

	@Override
	public void disableDepth() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public void enableDepth() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public void depthFunc(int func) {
		GL11.glDepthFunc(func);
	}

	@Override
	public void depthMask(boolean flag) {
		GL11.glDepthMask(flag);
	}

	@Override
	public void disableBlend() {
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void enableBlend() {
		GL11.glEnable(GL11.GL_BLEND);
	}

	@Override
	public void blendFunc(int srcFactor, int dstFactor) {
		GL11.glBlendFunc(srcFactor, dstFactor);
	}

	@Override
	public void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
		OpenGlHelper.glBlendFunc(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
	}

	@Override
	public void enableFog() {
		GL11.glEnable(GL11.GL_FOG);
	}

	@Override
	public void disableFog() {
		GL11.glDisable(GL11.GL_FOG);
	}

	@Override
	public void setFog(int param) {
		GL11.glFogi(GL11.GL_FOG_MODE, param);
	}

	@Override
	public void setFogDensity(float param) {
		GL11.glFogf(GL11.GL_FOG_DENSITY, param);
	}

	@Override
	public void setFogStart(float param) {
		GL11.glFogf(GL11.GL_FOG_START, param);
	}

	@Override
	public void setFogEnd(float param) {
		GL11.glFogf(GL11.GL_FOG_END, param);
	}

	@Override
	public void enableCull() {
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	@Override
	public void disableCull() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	@Override
	public void cullFace(int mode) {
		GL11.glCullFace(mode);
	}

	@Override
	public void enablePolygonOffset() {
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
	}

	@Override
	public void disablePolygonOffset() {
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
	}

	@Override
	public void doPolygonOffset(float factor, float units) {
		GL11.glPolygonOffset(factor, units);
	}

	@Override
	public void enableColorLogic() {
		GL11.glEnable(GL11.GL_COLOR_LOGIC_OP);
	}

	@Override
	public void disableColorLogic() {
		GL11.glDisable(GL11.GL_COLOR_LOGIC_OP);
	}

	@Override
	public void colorLogicOp(int opcode) {
		GL11.glLogicOp(opcode);
	}

	@Override
	public void setActiveTexture(int texture) {
		OpenGlHelper.setActiveTexture(texture);
	}

	@Override
	public void enableTexture2D() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void disableTexture2D() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void deleteTexture(int texture) {
		GL11.glDeleteTextures(texture);
	}

	@Override
	public void bindTexture(int texture) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
	}

	@Override
	public void enableNormalize() {
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	@Override
	public void disableNormalize() {
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	@Override
	public void shadeModel(int mode) {
		GL11.glShadeModel(mode);
	}

	@Override
	public void enableRescaleNormal() {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public void disableRescaleNormal() {
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public void viewport(int x, int y, int width, int height) {
		GL11.glViewport(x, y, width, height);
	}

	@Override
	public void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GL11.glColorMask(red, green, blue, alpha);
	}

	@Override
	public void clearDepth(double depth) {
		GL11.glClearDepth(depth);
	}

	@Override
	public void clearColor(float red, float green, float blue, float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
	}

	@Override
	public void clear(int mask) {
		GL11.glClear(mask);
	}

	@Override
	public void matrixMode(int mode) {
		GL11.glMatrixMode(mode);
	}

	@Override
	public void loadIdentity() {
		GL11.glLoadIdentity();
	}

	@Override
	public void pushMatrix() {
		GL11.glPushMatrix();
	}

	@Override
	public void popMatrix() {
		GL11.glPopMatrix();
	}

	@Override
	public void getFloat(int pname, FloatBuffer params) {
		GL11.glGetFloat(pname, params);
	}

	@Override
	public void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL11.glOrtho(left, right, bottom, top, zNear, zFar);
	}

	@Override
	public void rotate(float angle, float x, float y, float z) {
		GL11.glRotatef(angle, x, y, z);
	}

	@Override
	public void scale(float x, float y, float z) {
		GL11.glScalef(x, y, z);
	}

	@Override
	public void scale(double x, double y, double z) {
		GL11.glScaled(x, y, z);
	}

	@Override
	public void translate(float x, float y, float z) {
		GL11.glTranslatef(x, y, z);
	}

	@Override
	public void translate(double x, double y, double z) {
		GL11.glTranslated(x, y, z);
	}

	@Override
	public void multMatrix(FloatBuffer matrix) {
		GL11.glMultMatrix(matrix);
	}

	@Override
	public void color(float red, float green, float blue, float alpha) {
		GL11.glColor4f(red, green, blue, alpha);
	}

	@Override
	public void color(float red, float green, float blue) {
		GL11.glColor3f(red, green, blue);
	}

	@Override
	public void resetColor() {
		// does nothing when using GL directly
	}

	@Override
	public void callList(int list) {
		GL11.glCallList(list);
	}
}
