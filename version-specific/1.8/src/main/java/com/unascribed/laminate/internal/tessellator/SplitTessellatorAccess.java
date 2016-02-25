package com.unascribed.laminate.internal.tessellator;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public class SplitTessellatorAccess implements TessellatorAccess {

	private Format format;
	
	private double x, y, z;
	private float nx, ny, nz;
	private float r, g, b, a;
	private double u, v;
	
	private Tessellator tess() {
		return Tessellator.getInstance();
	}
	
	private WorldRenderer wr() {
		return tess().getWorldRenderer();
	}
	
	@Override
	public void begin(int mode, Format format) {
		this.format = format;
		wr().startDrawing(mode);
	}

	@Override
	public TessellatorAccess pos(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public TessellatorAccess color(float r, float g, float b) {
		return color(r, g, b, 1);
	}

	@Override
	public TessellatorAccess color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		return this;
	}
	
	@Override
	public TessellatorAccess color(int r, int g, int b) {
		color(r/255f, g/255f, b/255f);
		return this;
	}
	
	@Override
	public TessellatorAccess color(int r, int g, int b, int a) {
		color(r/255f, g/255f, b/255f, a/255f);
		return this;
	}

	@Override
	public TessellatorAccess normal(float x, float y, float z) {
		this.nx = x;
		this.ny = y;
		this.nz = z;
		return this;
	}

	@Override
	public TessellatorAccess tex(double u, double v) {
		this.u = u;
		this.v = v;
		return this;
	}

	@Override
	public void endVertex() {
		switch (format) {
			case POSITION:
				wr().addVertex(x, y, z);
				break;
			case POSITION_COLOR:
				wr().setColorRGBA_F(r, g, b, a);
				wr().addVertex(x, y, z);
				break;
			case POSITION_NORMAL:
				wr().setNormal(nx, ny, nz);
				wr().addVertex(x, y, z);
				break;
			case POSITION_TEX:
				wr().addVertexWithUV(x, y, z, u, v);
				break;
			case POSITION_TEX_COLOR:
				wr().setColorRGBA_F(r, g, b, a);
				wr().addVertexWithUV(x, y, z, u, v);
				break;
			case POSITION_TEX_COLOR_NORMAL:
				wr().setColorRGBA_F(r, g, b, a);
				wr().setNormal(nx, ny, nz);
				wr().addVertexWithUV(x, y, z, u, v);
				break;
			case POSITION_TEX_NORMAL:
				wr().setNormal(nx, ny, nz);
				wr().addVertexWithUV(x, y, z, u, v);
				break;
			default:
				throw new AssertionError("Unknown vertex format "+format);
		}
		x = y = z = 0;
		r = g = b = a = 1;
		nx = ny = nz = 0;
		u = v = 0;
	}

	@Override
	public void draw() {
		tess().draw();
		x = y = z = 0;
		r = g = b = a = 1;
		nx = ny = nz = 0;
		u = v = 0;
		format = null;
	}


}
