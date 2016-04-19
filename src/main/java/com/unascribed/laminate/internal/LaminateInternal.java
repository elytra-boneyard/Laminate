package com.unascribed.laminate.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.google.common.base.Function;
import com.unascribed.laminate.internal.gl.DirectGLAccess;
import com.unascribed.laminate.internal.gl.GLAccess;
import com.unascribed.laminate.internal.gl.StateManagerGLAccess;
import com.unascribed.laminate.internal.tessellator.OldTessellatorAccess;
import com.unascribed.laminate.internal.tessellator.SplitTessellatorAccess;
import com.unascribed.laminate.internal.tessellator.TessellatorAccess;
import com.unascribed.laminate.internal.tessellator.VertexBuilderTessellatorAccess;

import aesen.laminate.Laminate;
import aesen.laminate.component.Box;
import aesen.laminate.component.Label;
import aesen.laminate.component.Panel;
import aesen.laminate.screen.Screen;
import aesen.laminate.shadowbox.EndShadowbox;
import aesen.laminate.shadowbox.PanoramaShadowbox;
import aesen.laminate.shadowbox.SolidShadowbox;
import aesen.laminate.shadowbox.TextureShadowbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

/**
 * <b>Internal class. Do not use.</b>
 * 
 */
public class LaminateInternal implements LaminateCore {
	public static int globalPanoramaTimer;
	private static GLAccess gl;
	private static TessellatorAccess tess;
	private static Function<Minecraft, ScaledResolution> scaledResolutionFactory;
	public static final Logger log = LogManager.getLogger("Laminate");
	
	private static ScaledResolution cachedResolution;
	
	@Override
	public void preInit(String mcVersion) {
		switch (mcVersion) {
			case "1.7.2":
			case "1.7.10":
				tess = new OldTessellatorAccess();
				gl = new DirectGLAccess();
				scaledResolutionFactory = OneEight.SCALED_RESOLUTION_FACTORY;
				log.info("Running on "+mcVersion+", using old tessellator and direct GL");
				break;
			case "1.8":
				tess = new SplitTessellatorAccess();
				gl = new StateManagerGLAccess(true);
				scaledResolutionFactory = OneEight.SCALED_RESOLUTION_FACTORY;
				log.info("Running on "+mcVersion+", using split tessellator and managed GL with emulated pushAttrib/popAttrib");
				break;
			case "1.8.8":
			case "1.8.9":
				tess = new VertexBuilderTessellatorAccess();
				gl = new StateManagerGLAccess(true);
				scaledResolutionFactory = OneEightNine.SCALED_RESOLUTION_FACTORY;
				log.info("Running on "+mcVersion+", using vertex builder tessellator and managed GL with emulated pushAttrib/popAttrib");
				break;
			default:
				throw new RuntimeException("Laminate cannot run on Minecraft version "+mcVersion);
		}
	}
	
	@Override
	public void frame() {
		cachedResolution = null;
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
			} else if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
				Box group = new Box();
				Screen screen = new Screen() {
					@Override
					public void renderForeground(float partialTicks) {
						super.renderForeground(partialTicks);
						Minecraft.getMinecraft().fontRendererObj.drawString(Minecraft.getMinecraft().debug.split("fps ")[0]+"fps", width-100, height-36, 0);
						Minecraft.getMinecraft().fontRendererObj.drawString(group.size()+" components", width-100, height-24, 0);
					}
				};
				group.setX(0);
				group.setY(0);
				group.setWidth(99);
				group.setHeight(800);
				screen.setShadowbox(new SolidShadowbox(1, 1, 1));
				for (int i = 0; i < 10000; i++) {
					Box box = new Box();
					box.setColor(0xFF000000);
					box.setWidth(1);
					box.setHeight(1);
					box.setX((i*2)%99);
					box.setY((i*2)/99);
					group.add(box);
				}
				screen.add(group);
				Laminate.display(screen);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
				Screen screen = new Screen();
				Panel panel = new Panel();
				panel.setX(10);
				panel.setY(10);
				panel.setWidth(200);
				panel.setHeight(100);
				panel.setShowBorder(true);
				panel.setTitle("Hello");
				Box box = new Box();
				box.setColor(0xFFFF0000);
				box.setWidth(3000);
				box.setHeight(3000);
				panel.add(box);
				screen.add(panel);
				
				Panel panel2 = new Panel();
				panel2.setX(220);
				panel2.setY(10);
				panel2.setWidth(100);
				panel2.setHeight(100);
				panel2.setShowBorder(true);
				panel2.add(box); // should this be considered a bug?
				screen.add(panel2);
				
				Panel panel3 = new Panel();
				panel3.setX(10);
				panel3.setY(120);
				panel3.setWidth(100);
				panel3.setHeight(100);
				panel3.add(box);
				screen.add(panel3);
				
				Laminate.display(screen);
			} else if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
				Screen screen = new Screen();
				Label label = new Label();
				label.setX(10);
				label.setY(10);
				label.setText("Hello");
				screen.add(label);
				
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

	public static ScaledResolution createScaledResolution() {
		if (cachedResolution == null) {
			cachedResolution = scaledResolutionFactory.apply(Minecraft.getMinecraft());
		}
		return cachedResolution;
	}
}
