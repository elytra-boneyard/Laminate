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
	public void preInit() {
		log = LogManager.getLogger("Laminate");
		try {
			Class.forName("net.minecraft.client.renderer.GlStateManager");
			log.info("Using GlStateManager (>= 1.8)");
			gl = new StateManagerGLAccess();
		} catch (Exception ex) {
			log.info("Using direct GL (< 1.8)");
			gl = new DirectGLAccess();
		}
		try {
			Class<?> tessClazz = Class.forName("net.minecraft.client.renderer.Tessellator");
			try {
				tessClazz.getMethod("addVertex", double.class, double.class, double.class);
				log.info("Using OldTessellatorAccess (<= 1.7)");
				tess = new OldTessellatorAccess();
			} catch (Exception ex) {
				Class<?> wrClazz = Class.forName("net.minecraft.client.renderer.WorldRenderer");
				try {
					wrClazz.getMethod("addVertex", double.class, double.class, double.class);
					log.info("Using SplitTessellatorAccess (1.8)");
					tess = new SplitTessellatorAccess();
				} catch (Exception e) {
					log.info("Using VertexBuilderTessellatorAccess (>= 1.8.8)");
					tess = new VertexBuilderTessellatorAccess();
				}
			}
		} catch (Exception ex) {
			log.error("Can't find any tessellator!");
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
