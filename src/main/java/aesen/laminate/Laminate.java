package aesen.laminate;

import java.util.List;

import com.google.common.collect.Lists;
import com.unascribed.laminate.internal.LaminateInternal;

import aesen.laminate.screen.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * Main entry point for modifying the current GUI state.
 * This is where you'll add overlays, switch screens, and
 * all other such things.
 *
 */
public class Laminate {
	private static List<InputHook> inputHooks = Lists.newArrayList();
	private static List<Screen> overlays = Lists.newArrayList();
	
	/**
	 * Adds an input hook, which is essentially a lightweight
	 * Screen that receives input but does not render anything.
	 * <p>
	 * Unlike overlays, input hooks will not be cleared when the
	 * underlying GUI changes.
	 * @param hook the hook to add
	 */
	public static void addInputHook(InputHook hook) {
		inputHooks.add(hook);
	}
	
	/**
	 * Remove an input hook, making it no longer receive events.
	 * @param hook the hook to remove
	 */
	public static void removeInputHook(InputHook hook) {
		inputHooks.remove(hook);
	}
	
	
	
	/**
	 * Adds a Screen to the top of the overlay stack, rendering
	 * it over all existing overlays and the base GUI.
	 * @param screen the screen to add to the overlay stack
	 */
	public static void addOverlayToTop(Screen screen) {
		overlays.add(screen);
	}
	
	/**
	 * Adds a Screen to the bottom of the overlay stack, rendering
	 * it under all existing overlays, but still over the base GUI.
	 * @param screen the screen to add to the overlay stack
	 */
	public static void addOverlayToBottom(Screen screen) {
		overlays.add(0, screen);
	}
	
	/**
	 * Removes an overlay from the overlay stack, regardless of it's
	 * position. Once removed, this overlay will no longer render and
	 * will no longer receive events.
	 * @param screen the screen to remove from the overlay stack
	 */
	public static void removeOverlay(Screen screen) {
		overlays.remove(screen);
	}
	
	
	
	/**
	 * Sets the passed Screen as the currently displaying GUI, therefore
	 * clearing the overlay stack and closing the current GUI, be it a
	 * Screen or GuiScreen.
	 * @param screen the screen to display
	 */
	public static void display(Screen screen) {
		Minecraft.getMinecraft().displayGuiScreen(screen.getMirror());
	}
	
	/**
	 * Supplied for API consistency.
	 * Identical to {@code Minecraft.getMinecraft().displayGuiScreen(screen)}.
	 * @param screen the screen to display
	 */
	public static void display(GuiScreen screen) {
		Minecraft.getMinecraft().displayGuiScreen(screen);
	}

	
	
	/**
	 * Get the currently displaying GUI as a Screen, or null if it is a
	 * vanilla GuiScreen.
	 * @return the currently displaying Screen
	 */
	public static Screen getCurrentScreen() {
		return LaminateInternal.unwrapMirror(Minecraft.getMinecraft().currentScreen);
	}
	
	
	
	/**
	 * Get the width of the current GUI, avoiding extra object creation if possible.
	 */
	public static int getWidth() {
		if (Minecraft.getMinecraft().currentScreen != null) {
			return Minecraft.getMinecraft().currentScreen.width;
		}
		return LaminateInternal.createScaledResolution().getScaledWidth();
	}
	
	/**
	 * Get the height of the current GUI, avoiding extra object creation if possible.
	 */
	public static int getHeight() {
		if (Minecraft.getMinecraft().currentScreen != null) {
			return Minecraft.getMinecraft().currentScreen.height;
		}
		return LaminateInternal.createScaledResolution().getScaledHeight();
	}
	
	
	
	
	private Laminate() {} // static, do not allow instanciation
}
