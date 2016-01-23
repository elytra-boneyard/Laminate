package com.unascribed.laminate.internal;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;


@Mod(
		modid="laminate",
		name="Laminate",
		acceptableRemoteVersions="*",
		acceptedMinecraftVersions="1.7.2,1.7.10"
		)
public class LaminateModOneSeven {
	private LaminateCore core;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) throws Exception {
		if (e.getSide() == Side.CLIENT) {
			core = (LaminateCore)Class.forName("com.unascribed.laminate.internal.LaminateInternal").newInstance();
			core.preInit(Loader.instance().getMCVersionString().substring("Minecraft ".length()));
		} else {
			e.getModLog().warn("Cowardly refusing to run on side {}", e.getSide());
		}
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		if (e.getSide() == Side.CLIENT) {
			FMLCommonHandler.instance().bus().register(this);
			MinecraftForge.EVENT_BUS.register(this);
		}
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent e) {
		core.tick(e.phase == Phase.START);
	}
}