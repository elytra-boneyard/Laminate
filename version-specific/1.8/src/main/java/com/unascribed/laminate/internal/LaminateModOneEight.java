package com.unascribed.laminate.internal;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

@Mod(
		modid="laminate",
		name="Laminate",
		acceptableRemoteVersions="*",
		clientSideOnly=true,
		acceptedMinecraftVersions="1.8,1.8.8,1.8.9"
		)
public class LaminateModOneEight {
	private LaminateCore core;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) throws Exception {
		core = (LaminateCore)Class.forName("com.unascribed.laminate.internal.LaminateInternal").newInstance();
		core.preInit(Loader.instance().getMCVersionString().substring("Minecraft ".length()));
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent e) {
		core.tick(e.phase == Phase.START);
	}
	
	@SubscribeEvent
	public void onFrame(RenderTickEvent e) {
		if (e.phase == Phase.START) {
			core.frame();
		}
	}
}
