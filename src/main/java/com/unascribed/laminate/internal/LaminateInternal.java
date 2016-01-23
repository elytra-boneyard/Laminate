package com.unascribed.laminate.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.unascribed.laminate.internal.gl.DirectGLAccess;
import com.unascribed.laminate.internal.gl.GLAccess;
import com.unascribed.laminate.internal.gl.StateManagerGLAccess;
import com.unascribed.laminate.internal.tessellator.OldTessellatorAccess;
import com.unascribed.laminate.internal.tessellator.SplitTessellatorAccess;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess;
import com.unascribed.laminate.internal.tessellator.VertexBuilderTessellatorAccess;

import aesen.laminate.Laminate;
import aesen.laminate.screen.Screen;
import aesen.laminate.shadowbox.EndShadowbox;
import aesen.laminate.shadowbox.PanoramaShadowbox;
import aesen.laminate.shadowbox.SolidShadowbox;
import aesen.laminate.shadowbox.TextureShadowbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

/**
 * <b>Internal class. Do not use.</b>
 *
 */
public class LaminateInternal implements LaminateCore {
	public static int globalPanoramaTimer;
	private static GLAccess gl;
	private static TessellatorAccess tess;
	public static Logger log;
	
	@Override
	public void preInit(String mcVersion) {
		log = LogManager.getLogger("Laminate");
		switch (mcVersion) {
			case "1.7.2":
			case "1.7.10":
				tess = new OldTessellatorAccess();
				gl = new DirectGLAccess();
				log.info("Running on "+mcVersion+", using old tessellator and direct GL");
				break;
			case "1.8":
				tess = new SplitTessellatorAccess();
				gl = new StateManagerGLAccess();
				log.info("Running on "+mcVersion+", using split tessellator and managed GL");
				break;
			case "1.8.8":
			case "1.8.9":
				tess = new VertexBuilderTessellatorAccess();
				gl = new StateManagerGLAccess();
				log.info("Running on "+mcVersion+", using vertex builder tessellator and managed GL");
				break;
			default:
				throw new RuntimeException("Laminate cannot run on Minecraft version "+mcVersion);
		}
	}
	
	@Override
	public void tick(boolean start) {
		if (start) {
			if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
				Screen screen = new Screen();
				screen.setShadowbox(new PanoramaShadowbox());
				Laminate.display(screen);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				Screen screen = new Screen();
				screen.setShadowbox(new EndShadowbox());
				Laminate.display(screen);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
				Screen screen = new Screen();
				screen.setShadowbox(new TextureShadowbox());
				Laminate.display(screen);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
				Screen screen = new Screen();
				screen.setShadowbox(new SolidShadowbox(1, 0.5f, 0));
				Laminate.display(screen);
			}
		} else {
			GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
			if (currentScreen instanceof GuiMainMenu) {
				((GuiMainMenu)Minecraft.getMinecraft().currentScreen).panoramaTimer = globalPanoramaTimer;
			}
			globalPanoramaTimer++;
		}
	}

	public static GuiScreen createMirror(Screen screen) {
		return new Mirror(screen);
	}

	public static Screen unwrapMirror(GuiScreen screen) {
		if (screen instanceof Mirror) {
			return ((Mirror)screen).getScreen();
		}
		return null;
	}
	
	public static GLAccess gl() {
		return gl;
	}
	
	public static TessellatorAccess tess() {
		return tess;
	}
}
