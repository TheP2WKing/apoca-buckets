package net.thep2wking.apocabuckets.content.itemblock;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thep2wking.oedldoedlcore.api.item.ModItemBase;

public class ItemBlockFire extends ModItemBase {
	public ItemBlockFire(String modid, String name, CreativeTabs tab, EnumRarity rarity, boolean hasEffect, int tooltipLines,
			int annotationLines) {
		super(modid, name, tab, rarity, hasEffect, tooltipLines, annotationLines);
		this.setUnlocalizedName("tile." + modid + "." + name);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
        return "tile." + modid + "." + name;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		if (!player.capabilities.isCreativeMode) {
			return EnumActionResult.FAIL;
		}
		if (!block.isReplaceable(worldIn, pos)) {
			pos = pos.offset(facing);
		}
		ItemStack itemstack = player.getHeldItem(hand);
		if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack)
				&& worldIn.mayPlace(Blocks.FIRE, pos, false, facing, player)) {
			int i = this.getMetadata(itemstack.getMetadata());
			IBlockState iblockstate1 = Blocks.FIRE.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, i,
					player, hand);
			if (placeBlockAt(itemstack, player, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1)) {
				iblockstate1 = worldIn.getBlockState(pos);
				SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, player);
				worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
						(soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				itemstack.shrink(1);
			}
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, IBlockState newState) {
		if (!world.setBlockState(pos, newState, 11))
			return false;
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == Blocks.FIRE) {
			setTileEntityNBT(world, player, pos, stack);
			Blocks.FIRE.onBlockPlacedBy(world, pos, state, player, stack);
			if (player instanceof EntityPlayerMP)
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, stack);
		}
		return true;
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos,
			ItemStack stackIn) {
		MinecraftServer minecraftserver = worldIn.getMinecraftServer();
		if (minecraftserver == null) {
			return false;
		} else {
			NBTTagCompound nbttagcompound = stackIn.getSubCompound("BlockEntityTag");
			if (nbttagcompound != null) {
				TileEntity tileentity = worldIn.getTileEntity(pos);
				if (tileentity != null) {
					if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt()
							&& (player == null || !player.canUseCommandBlock())) {
						return false;
					}
					NBTTagCompound nbttagcompound1 = tileentity.writeToNBT(new NBTTagCompound());
					NBTTagCompound nbttagcompound2 = nbttagcompound1.copy();
					nbttagcompound1.merge(nbttagcompound);
					nbttagcompound1.setInteger("x", pos.getX());
					nbttagcompound1.setInteger("y", pos.getY());
					nbttagcompound1.setInteger("z", pos.getZ());
					if (!nbttagcompound1.equals(nbttagcompound2)) {
						tileentity.readFromNBT(nbttagcompound1);
						tileentity.markDirty();
						return true;
					}
				}
			}
			return false;
		}
	}
}