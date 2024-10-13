package net.thep2wking.apocabuckets.content.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.oedldoedlcore.api.block.ModBlockBase;
import net.thep2wking.oedldoedlcore.util.ModToolTypes;

public class BlockGreenFire extends ModBlockBase {
	public BlockGreenFire(String modid, String name, CreativeTabs tab, Material material, SoundType sound,
			MapColor mapColor, int harvestLevel, ModToolTypes toolType, float hardness, float resistance,
			int lightLevel) {
		super(modid, name, tab, material, sound, mapColor, harvestLevel, toolType, hardness, resistance, lightLevel);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModItems.FIRE_BUCKET);
	}

	public boolean isDisabled(World world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.FIRE
				|| !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(24) == 0) {
			worldIn.playSound((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F),
					(double) ((float) pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS,
					1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        if (isDisabled(world)) {
            world.scheduleUpdate(pos, this, this.tickRate(world));
            return;
        }

        if (!world.isRemote && world instanceof WorldServer) {
            ((WorldServer) world).addScheduledTask(() -> {
                boolean shouldUpdate = false;
                EnumFacing[] directions = { EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN };
                for (EnumFacing direction : directions) {
                    BlockPos targetPos = pos.offset(direction);
                    IBlockState targetState = world.getBlockState(targetPos);
                    if ((world.isAirBlock(targetPos) || targetState.getBlock().isReplaceable(world, targetPos)) &&
                            !targetState.getMaterial().isLiquid()) {
                        world.setBlockState(targetPos, this.getDefaultState());
                        shouldUpdate = true;
                    }
                }
                if (shouldUpdate) {
                    world.scheduleUpdate(pos, this, this.tickRate(world));
                } else {
                    world.setBlockToAir(pos);
                }
            });
        }
    }

	@Override
	public int tickRate(World worldIn) {
		return 10;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
}
