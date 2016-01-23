package com.unascribed.laminate.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.unascribed.laminate.internal.gl.DirectGLAccess;
import com.unascribed.laminate.internal.gl.GLAccess;
import com.unascribed.laminate.internal.gl.StateManagerGLAccess;

import aesen.laminate.Laminate;
import aesen.laminate.screen.Screen;
import aesen.laminate.shadowbox.EndShadowbox;
import aesen.laminate.shadowbox.PanoramaShadowbox;
import aesen.laminate.shadowbox.SolidShadowbox;
import aesen.laminate.shadowbox.TextureShadowbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/**
 * <b>Internal class. Do not use.</b>
 *
 */
@Mod(
	modid="laminate",
	name="Laminate",
	version="0.1",
	clientSideOnly=true,
	acceptableRemoteVersions="*",
	acceptedMinecraftVersions="1.7.10,1.8,1.8.8,1.8.9"
	)
public class LaminateMod {
	public static int globalPanoramaTimer;
	private static GLAccess gl;
	public static Logger log;
	
	@EventHandler
	private void onPreInit(FMLPreInitializationEvent e) {
		log = LogManager.getLogger("Laminate");
		try {
			Class.forName("net.minecraft.client.renderer.GlStateManager");
			log.info("Using GlStateManager");
			gl = new StateManagerGLAccess();
		} catch (Exception ex) {
			log.info("Using direct GL");
			gl = new DirectGLAccess();
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onInit(FMLInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(this); // for 1.7.10 compat
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent e) {
		if (e.phase == Phase.START) {
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
		} else if (e.phase == Phase.END) {
			GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
			if (currentScreen instanceof GuiMainMenu) {
				((GuiMainMenu)Minecraft.getMinecraft().currentScreen).panoramaTimer = globalPanoramaTimer;
			} else if (currentScreen != null && currentScreen.getClass().getCanonicalName().equals("lumien.custommainmenu.gui.GuiCustom")) {
				ReflectionHelper.setPrivateValue((Class<GuiScreen>)currentScreen.getClass(), currentScreen, globalPanoramaTimer, "panoramaTimer");
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
}
