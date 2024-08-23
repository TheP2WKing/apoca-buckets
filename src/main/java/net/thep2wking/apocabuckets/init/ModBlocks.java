package net.thep2wking.apocabuckets.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.content.block.BlockFluidFrozen;
import net.thep2wking.apocabuckets.content.block.BlockFluidLavaTsunami;
import net.thep2wking.apocabuckets.content.block.BlockFluidToxic;
import net.thep2wking.apocabuckets.content.block.BlockFluidTsunami;
import net.thep2wking.apocabuckets.content.block.BlockFluidVoid;
import net.thep2wking.apocabuckets.content.block.BlockGreenFire;
import net.thep2wking.apocabuckets.content.block.BlockPurplePaint;
import net.thep2wking.apocabuckets.content.block.BlockWhitePlaint;
import net.thep2wking.oedldoedlcore.util.ModToolTypes;

public class ModBlocks {
	public static final Block WHITE_PAINT = new BlockWhitePlaint(ApocaBuckets.MODID, "white_paint", ApocaBuckets.TAB, Material.ROCK, SoundType.STONE, MapColor.SNOW, 0, ModToolTypes.PICKAXE, 0F, 0F, 0);
	public static final Block PURPLE_PAINT = new BlockPurplePaint(ApocaBuckets.MODID, "purple_paint", ApocaBuckets.TAB, Material.ROCK, SoundType.STONE, MapColor.PURPLE, 0, ModToolTypes.PICKAXE, 0F, 0F, 0);
	public static final Block GREEN_FIRE = new BlockGreenFire(ApocaBuckets.MODID, "green_fire", ApocaBuckets.TAB, Material.FIRE, SoundType.CLOTH, MapColor.GREEN, 0, ModToolTypes.NO_TOOL, 0F, 0F, 15);

	public static final BlockFluidClassic TSUNAMI = new BlockFluidTsunami(ApocaBuckets.MODID, "tsunami", ModFluids.TSUNAMI, 0x3f76e4, Material.WATER, MapColor.WATER);
	public static final BlockFluidClassic LAVA_TSUNAMI = new BlockFluidLavaTsunami(ApocaBuckets.MODID, "lava_tsunami", ModFluids.LAVA_TSUNAMI, 0xff4500, Material.LAVA, MapColor.ADOBE);
	public static final BlockFluidClassic FROZEN = new BlockFluidFrozen(ApocaBuckets.MODID, "frozen", ModFluids.FROZEN, 0x37fdfc, Material.WATER, MapColor.ICE);
	public static final BlockFluidClassic TOXIC = new BlockFluidToxic(ApocaBuckets.MODID, "toxic", ModFluids.TOXIC, 0x4dfe15, Material.WATER, MapColor.LIME);
	public static final BlockFluidClassic VOID = new BlockFluidVoid(ApocaBuckets.MODID, "void", ModFluids.VOID, 0x000000, Material.LAVA, MapColor.BLACK);
}