package com.unascribed.laminate.internal.tessellator;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class VertexBuilderTessellatorAccess implements TessellatorAccess {
	private Map<Format, VertexFormat> formats = ImmutableMap.<Format, VertexFormat>builder()
			.put(Format.POSITION, DefaultVertexFormats.POSITION)
			.put(Format.POSITION_COLOR, DefaultVertexFormats.POSITION_COLOR)
			.put(Format.POSITION_NORMAL, DefaultVertexFormats.POSITION_NORMAL)
			.put(Format.POSITION_TEX, DefaultVertexFormats.POSITION_TEX)
			.put(Format.POSITION_TEX_COLOR, DefaultVertexFormats.POSITION_TEX_COLOR)
			.put(Format.POSITION_TEX_COLOR_NORMAL, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL)
			.put(Format.POSITION_TEX_NORMAL, DefaultVertexFormats.POSITION_TEX_NORMAL)
			.build();
	
	private WorldRenderer wr() {
		return Tessellator.getInstance().getWorldRenderer();
	}
	
	@Override
	public void begin(int mode, Format format) {
		wr().begin(mode, formats.get(format));
	}

	@Override
	public TessellatorAccess pos(double x, double y, double z) {
		wr().pos(x, y, z);
		return this;
	}

	@Override
	public TessellatorAccess color(float r, float g, float b) {
		wr().color(r, g, b, 1);
		return this;
	}

	@Override
	public TessellatorAccess color(float r, float g, float b, float a) {
		wr().color(r, g, b, a);
		return this;
	}
	
	@Override
	public TessellatorAccess color(int r, int g, int b) {
		wr().color(r, g, b, 255);
		return this;
	}
	
	@Override
	public TessellatorAccess color(int r, int g, int b, int a) {
		wr().color(r, g, b, a);
		return this;
	}

	@Override
	public TessellatorAccess normal(float x, float y, float z) {
		wr().normal(x, y, z);
		return this;
	}

	@Override
	public TessellatorAccess tex(double u, double v) {
		wr().tex(u, v);
		return this;
	}

	@Override
	public void endVertex() {
		wr().endVertex();
	}

	@Override
	public void draw() {
		Tessellator.getInstance().draw();
	}

}
