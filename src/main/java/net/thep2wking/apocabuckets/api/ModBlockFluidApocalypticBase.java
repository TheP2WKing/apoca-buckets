package net.thep2wking.apocabuckets.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.thep2wking.oedldoedlcore.api.fluid.ModBlockFluidBase;

public abstract class ModBlockFluidApocalypticBase extends ModBlockFluidBase {
	public ModBlockFluidApocalypticBase(String modid, String name, Fluid fluid, int fogColor, Material material,
			MapColor mapColor) {
		super(modid, name, fluid, fogColor, material, mapColor);
	}

	public ModBlockFluidApocalypticBase(String modid, String name, Fluid fluid, Material material, MapColor mapColor) {
		super(modid, name, fluid, material, mapColor);
	}

	public abstract ItemStack getBucket();
}