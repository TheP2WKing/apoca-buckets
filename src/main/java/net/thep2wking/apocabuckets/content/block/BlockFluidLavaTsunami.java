package net.thep2wking.apocabuckets.content.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.Fluid;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;

public class BlockFluidLavaTsunami extends BlockFluidTsunami {
    public BlockFluidLavaTsunami(String modid, String name, Fluid fluid, int fogColor, Material material,
            MapColor mapColor) {
        super(modid, name, fluid, fogColor, material, mapColor);
    }

    @Override
    public ItemStack getBucket() {
        return new ItemStack(ModItems.LAVA_TSUNAMI_BUCKET);
    }

    @Override
    public boolean isDisabled(WorldServer world) {
        return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.LAVA_TSUNAMI
                || !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
    }
}