package com.unascribed.laminate.internal;

import org.lwjgl.input.Keyboard;

import aesen.laminate.Laminate;
import aesen.laminate.screen.Screen;
import aesen.laminate.shadowbox.EndShadowbox;
import aesen.laminate.shadowbox.PanoramaShadowbox;
import aesen.laminate.shadowbox.TextureShadowbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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

	@EventHandler
	private void onInit(FMLInitializationEvent e) {
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
}
