package net.thep2wking.apocabuckets.content.block;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.oedldoedlcore.api.block.ModBlockBase;
import net.thep2wking.oedldoedlcore.util.ModToolTypes;

public class BlockWhitePlaint extends ModBlockBase {
	private int[] spreadTo = new int[] { 1, 0, 0, 0, 1, 0, 0, 0, 1, -1, 0, 0, 0, -1, 0, 0, 0, -1 };

	public BlockWhitePlaint(String modid, String name, CreativeTabs tab, Material material, SoundType sound,
			MapColor mapColor, int harvestLevel, ModToolTypes toolType, float hardness, float resistance,
			int lightLevel) {
		super(modid, name, tab, material, sound, mapColor, harvestLevel, toolType, hardness, resistance, lightLevel);
		setTickRandomly(true);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModItems.WHITE_PAINT_BUCKET);
	}

	public boolean isDisabled(World world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.WHITE_PAINT
				|| !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
	}

	public boolean getShouldDecay() {
		return false;
	}

	public int getSpreadSpeed() {
		return 20;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleUpdate(pos, this, getSpreadSpeed());
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		boolean shouldUpdate = false;
		if (!isDisabled(world)) {
			for (int i = 0; i < this.spreadTo.length; i += 3) {
				BlockPos targetPos = pos.add(this.spreadTo[i], this.spreadTo[i + 1], this.spreadTo[i + 2]);
				if (world.isAirBlock(targetPos) || world.getBlockState(targetPos).getBlock() == this)
					continue;
				world.setBlockState(targetPos, this.getDefaultState());
				shouldUpdate = true;
			}

			if (shouldUpdate) {
				world.scheduleUpdate(pos, this, getSpreadSpeed());
			} else if (getShouldDecay()) {
				world.setBlockToAir(pos);
			}
		}
		world.scheduleUpdate(pos, this, getSpreadSpeed());
	}
}