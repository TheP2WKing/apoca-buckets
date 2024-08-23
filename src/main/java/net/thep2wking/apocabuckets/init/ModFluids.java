package net.thep2wking.apocabuckets.init;

import net.minecraftforge.fluids.Fluid;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.oedldoedlcore.api.fluid.ModFluidBase;
import net.thep2wking.oedldoedlcore.util.ModFluidUtil;
import net.thep2wking.oedldoedlcore.util.ModRarities;

public class ModFluids {
	public static final Fluid TSUNAMI = new ModFluidBase(ApocaBuckets.MODID, "tsunami", ModFluidUtil.WATER_STILL, ModFluidUtil.WATER_FLOW, 500, 1500, 1500, 0, false, ModRarities.LIGHT_PURPLE);
	public static final Fluid LAVA_TSUNAMI = new ModFluidBase(ApocaBuckets.MODID, "lava_tsunami", ModFluidUtil.LAVA_STILL, ModFluidUtil.LAVA_FLOW, 2000, 2000, 10000, 15, false, ModRarities.LIGHT_PURPLE);
	public static final Fluid FROZEN = new ModFluidBase(ApocaBuckets.MODID, "frozen", ModFluidUtil.LIQUID_STILL_BASE, ModFluidUtil.LIQUID_FLOW_BASE, 0x1692b8, 500, 1500, 1500, 0, false, ModRarities.LIGHT_PURPLE);
	public static final Fluid TOXIC = new ModFluidBase(ApocaBuckets.MODID, "toxic", ModFluidUtil.LIQUID_STILL_BASE, ModFluidUtil.LIQUID_FLOW_BASE, 0x2b6e16, 500, 1500, 1500, 0, false, ModRarities.LIGHT_PURPLE);
	public static final Fluid VOID = new ModFluidBase(ApocaBuckets.MODID, "void", ModFluidUtil.MOLTEN_STILL_BASE, ModFluidUtil.MOLTEN_FLOW_BASE, 0x000000, 2000, 2000, 10000, 0, false, ModRarities.LIGHT_PURPLE);
}