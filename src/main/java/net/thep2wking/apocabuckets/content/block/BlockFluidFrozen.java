package net.thep2wking.apocabuckets.content.block;

import java.util.Random;

import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.thep2wking.apocabuckets.api.ModBlockFluidApocalypticBase;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.oedldoedlcore.util.ModPotionUtil;

public class BlockFluidFrozen extends ModBlockFluidApocalypticBase {
	public BlockFluidFrozen(String modid, String name, Fluid fluid, int fogColor, Material material,
			MapColor mapColor) {
		super(modid, name, fluid, fogColor, material, mapColor);
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(ModItems.FROZEN_BUCKET);
	}

	public boolean isDisabled(World world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.FROZEN
				|| !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);

		ModPotionUtil.addEffectNotInCreativeMode(entityIn, MobEffects.SLOWNESS, 20 * 60 * 10, 99);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (isDisabled(world)) {
			return;
		}
		if (!world.isRemote) {
			if (shouldBeIce(world, pos, rand)) {
				IBlockState newState = (state.getBlock() instanceof BlockIce
						|| state.getBlock() instanceof BlockPackedIce) ? Blocks.PACKED_ICE.getDefaultState()
								: Blocks.ICE.getDefaultState();
				world.setBlockState(pos, newState);
			} else {
				world.setBlockState(pos, state.withProperty(LEVEL, 0), 2);
			}
		}
	}

	public boolean shouldBeIce(World world, BlockPos pos, Random rand) {
		int frozenNeighbors = 0;
		for (EnumFacing direction : EnumFacing.values()) {
			if (world.getBlockState(pos.offset(direction)).getBlock() instanceof BlockFluidFrozen) {
				frozenNeighbors++;
			}
		}
		if (frozenNeighbors == 0) {
			for (EnumFacing horizontal : EnumFacing.HORIZONTALS) {
				if (world.getBlockState(pos.offset(horizontal).down()).getBlock() instanceof BlockFluidFrozen) {
					frozenNeighbors++;
				}
			}
		}
		if (rand.nextInt(32) == 0 && frozenNeighbors >= 3) {
			BlockPos randomPos = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
			if (world.getBlockState(randomPos).getBlock() instanceof BlockIce) {
				world.setBlockState(randomPos, Blocks.ICE.getDefaultState());
			}
		}
		return frozenNeighbors >= 3;
	}

}
