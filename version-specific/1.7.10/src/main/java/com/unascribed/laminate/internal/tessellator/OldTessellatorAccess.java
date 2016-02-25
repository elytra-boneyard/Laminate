package com.unascribed.laminate.internal.tessellator;

import net.minecraft.client.renderer.Tessellator;

public class OldTessellatorAccess implements TessellatorAccess {

	private Format format;
	
	private double x, y, z;
	private float nx, ny, nz;
	private float r, g, b, a;
	private double u, v;
	
	private Tessellator tess() {
		return Tessellator.instance;
	}
	
	@Override
	public void begin(int mode, Format format) {
		this.format = format;
		tess().startDrawing(mode);
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
				tess().addVertex(x, y, z);
				break;
			case POSITION_COLOR:
				tess().setColorRGBA_F(r, g, b, a);
				tess().addVertex(x, y, z);
				break;
			case POSITION_NORMAL:
				tess().setNormal(nx, ny, nz);
				tess().addVertex(x, y, z);
				break;
			case POSITION_TEX:
				tess().addVertexWithUV(x, y, z, u, v);
				break;
			case POSITION_TEX_COLOR:
				tess().setColorRGBA_F(r, g, b, a);
				tess().addVertexWithUV(x, y, z, u, v);
				break;
			case POSITION_TEX_COLOR_NORMAL:
				tess().setColorRGBA_F(r, g, b, a);
				tess().setNormal(nx, ny, nz);
				tess().addVertexWithUV(x, y, z, u, v);
				break;
			case POSITION_TEX_NORMAL:
				tess().setNormal(nx, ny, nz);
				tess().addVertexWithUV(x, y, z, u, v);
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
