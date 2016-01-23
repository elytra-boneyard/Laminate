package com.unascribed.laminate.internal.tessellator;

public interface TessellatorAccess {
	public static enum Format {
		POSITION,
		POSITION_COLOR,
		POSITION_NORMAL,
		POSITION_TEX,
		POSITION_TEX_COLOR,
		POSITION_TEX_COLOR_NORMAL,
		POSITION_TEX_NORMAL
	}
	
	void begin(int mode, Format format);
	
	TessellatorAccess pos(double x, double y, double z);
	
	TessellatorAccess color(float r, float g, float b);
	TessellatorAccess color(float r, float g, float b, float a);
	TessellatorAccess color(int r, int g, int b);
	TessellatorAccess color(int r, int g, int b, int a);
	
	TessellatorAccess normal(float x, float y, float z);
	
	TessellatorAccess tex(double u, double v);
	
	void endVertex();
	
	void draw();
}

