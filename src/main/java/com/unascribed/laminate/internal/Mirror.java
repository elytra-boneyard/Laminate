package com.unascribed.laminate.internal;

import aesen.laminate.screen.Screen;
import net.minecraft.client.gui.GuiScreen;

class Mirror extends GuiScreen {
	private final Screen screen;
	public Mirror(Screen screen) {
		this.screen = screen;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		screen.renderShadowbox(partialTicks);
		screen.renderComponents(partialTicks);
	}
	
	@Override
	public void updateScreen() {
		screen.tick();
	}
}
