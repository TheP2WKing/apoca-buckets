package net.thep2wking.apocabuckets.content.block;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.Fluid;
import net.thep2wking.apocabuckets.api.ModBlockFluidApocalypticBase;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.oedldoedlcore.util.ModPotionUtil;

public class BlockFluidVoid extends ModBlockFluidApocalypticBase {
	public BlockFluidVoid(String modid, String name, Fluid fluid, int fogColor, Material material, MapColor mapColor) {
		super(modid, name, fluid, fogColor, material, mapColor);
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(ModItems.VOID_BUCKET);
	}

	public boolean isDisabled(World world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DISASTER.VOID
				|| !ApocaBucketsConfig.DISASTER.ENABLE_ALL_DISASTERS;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		ModPotionUtil.addEffectNotInCreativeMode(entityIn, MobEffects.BLINDNESS, 20 * 60 * 10, 99);
	}

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        if (isDisabled(world)) {
            return;
        }
        int level = state.getValue(LEVEL);
        if (!world.isRemote && world instanceof WorldServer) {
            ((WorldServer) world).addScheduledTask(() -> spreadFluid(world, pos, state, rand, level));
        }
    }

    private void spreadFluid(World world, BlockPos pos, IBlockState state, Random rand, int level) {
        if (level > 0) {
            if (world.getBlockState(pos).getBlock() == this) {
                world.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, 0), 2);
            }
        }
        for (EnumFacing direction : EnumFacing.HORIZONTALS) {
            BlockPos adjacentPos = pos.offset(direction);
            IBlockState adjacentState = world.getBlockState(adjacentPos);
            if (adjacentState.getMaterial() == Material.AIR) {
                world.setBlockState(adjacentPos, this.getDefaultState().withProperty(LEVEL, 0), 2);
            }
        }
        BlockPos belowPos = pos.down();
        IBlockState belowState = world.getBlockState(belowPos);
        if (belowState.getMaterial() == Material.AIR) {
            world.setBlockState(belowPos, this.getDefaultState().withProperty(LEVEL, 0), 2);
        }
    }
}