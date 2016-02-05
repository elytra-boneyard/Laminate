package com.unascribed.laminate.internal;

import com.google.common.base.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class OneEightNine {
	public static final Function<Minecraft, ScaledResolution> SCALED_RESOLUTION_FACTORY = ScaledResolution::new;
}
