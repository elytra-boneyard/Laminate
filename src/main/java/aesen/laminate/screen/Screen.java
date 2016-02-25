package aesen.laminate.screen;

import com.unascribed.laminate.internal.LaminateInternal;

import aesen.laminate.Container;
import aesen.laminate.InputHook;
import aesen.laminate.Rendering;
import aesen.laminate.component.Component;
import aesen.laminate.shadowbox.Shadowbox;
import aesen.laminate.shadowbox.TextureShadowbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * A collection of {@link Component}s, which can be displayed as either
 * an overlay or a fullscreen GUI.
 */
public class Screen extends Container implements InputHook {
	private GuiScreen mirror;
	private Shadowbox shadowbox = new TextureShadowbox();
	
	
	
	/**
	 * Render this screen's shadowbox. Will not be called if this
	 * Screen is being used as an overlay rather than a GUI.
	 */
	public void renderShadowbox(float partialTicks) {
		if (shadowbox != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				Rendering.drawGradientRect(0, 0, width, height, 0xC0101010, 0xD0101010);
			} else {
				shadowbox.render(partialTicks);
			}
		}
	}
	
	/**
	 * Render this screen's foreground, usually only consisting
	 * of it's children.
	 */
	public void renderForeground(float partialTicks) {
		renderChildren(partialTicks);
	}
	
	
	public void tick() {
		if (shadowbox != null) {
			shadowbox.tick();
		}
	}

	
	
	public GuiScreen getMirror() {
		if (mirror == null) {
			mirror = LaminateInternal.createMirror(this);
		}
		return mirror;
	}
	
	public void setShadowbox(Shadowbox shadowbox) {
		this.shadowbox = shadowbox;
	}
	
	public Shadowbox getShadowbox() {
		return shadowbox;
	}
	
	
	
	@Override
	public void onKeyDown(char keyChar, int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyUp(char keyChar, int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDown(int x, int y, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseUp(int x, int y, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseScroll(int x, int y, int scrollAmount) {
		// TODO Auto-generated method stub
		
	}

}
