package net.thep2wking.apocabuckets.api;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thep2wking.oedldoedlcore.config.CoreConfig;
import net.thep2wking.oedldoedlcore.util.ModTooltips;

public class ModItemApocalypticBucketBase extends ItemBucket {
	public final String modid;
	public final String name;
	public final CreativeTabs tab;
	public final Block containedBlock;
	public final String containedEntity;
	public final boolean hasCustomBehavior;
	public final EnumRarity rarity;
	public final boolean hasEffect;
	public final int tooltipLines;
	public final int annotationLines;

	public ModItemApocalypticBucketBase(String modid, String name, CreativeTabs tab, Block containedBlock,
			EnumRarity rarity, boolean hasEffect, int tooltipLines, int annotationLines) {
		super(containedBlock);
		this.modid = modid;
		this.name = name;
		this.tab = tab;
		this.containedBlock = containedBlock;
		this.containedEntity = null;
		this.hasCustomBehavior = false;
		this.rarity = rarity;
		this.hasEffect = hasEffect;
		this.tooltipLines = tooltipLines;
		this.annotationLines = annotationLines;
		setUnlocalizedName(this.modid + "." + this.name);
		setRegistryName(this.modid + ":" + this.name);
		setCreativeTab(this.tab);
		setMaxStackSize(1);
	}

	public ModItemApocalypticBucketBase(String modid, String name, CreativeTabs tab, String containedEntity,
			EnumRarity rarity, boolean hasEffect, int tooltipLines, int annotationLines) {
		super(null);
		this.modid = modid;
		this.name = name;
		this.tab = tab;
		this.containedBlock = null;
		this.containedEntity = containedEntity;
		this.hasCustomBehavior = false;
		this.rarity = rarity;
		this.hasEffect = hasEffect;
		this.tooltipLines = tooltipLines;
		this.annotationLines = annotationLines;
		setUnlocalizedName(this.modid + "." + this.name);
		setRegistryName(this.modid + ":" + this.name);
		setCreativeTab(this.tab);
		setMaxStackSize(1);
	}

	public ModItemApocalypticBucketBase(String modid, String name, CreativeTabs tab, EnumRarity rarity,
			boolean hasEffect, int tooltipLines, int annotationLines) {
		super(null);
		this.modid = modid;
		this.name = name;
		this.tab = tab;
		this.containedBlock = null;
		this.containedEntity = null;
		this.hasCustomBehavior = true;
		this.rarity = rarity;
		this.hasEffect = hasEffect;
		this.tooltipLines = tooltipLines;
		this.annotationLines = annotationLines;
		setUnlocalizedName(this.modid + "." + this.name);
		setRegistryName(this.modid + ":" + this.name);
		setCreativeTab(this.tab);
		setMaxStackSize(1);
	}

	private boolean isBeaconPayment;

	public Item setBeaconPayment() {
		isBeaconPayment = CoreConfig.PROPERTIES.BEACONS.BEACON_PAYMENTS;
		return this;
	}

	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return isBeaconPayment;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		if (!stack.isItemEnchanted() && CoreConfig.PROPERTIES.COLORFUL_RARITIES) {
			return this.rarity;
		} else if (stack.isItemEnchanted()) {
			switch (this.rarity) {
				case COMMON:
				case UNCOMMON:
					return EnumRarity.RARE;
				case RARE:
					return EnumRarity.EPIC;
				case EPIC:
				default:
					return this.rarity;
			}
		}
		return EnumRarity.COMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if (CoreConfig.PROPERTIES.ENCHANTMENT_EFFECTS) {
			return this.hasEffect || stack.isItemEnchanted();
		}
		return stack.isItemEnchanted();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (ModTooltips.showAnnotationTip()) {
			for (int i = 1; i <= annotationLines; ++i) {
				ModTooltips.addAnnotation(tooltip, this.getUnlocalizedName(), i);
			}
		}
		if (ModTooltips.showInfoTip()) {
			for (int i = 1; i <= tooltipLines; ++i) {
				ModTooltips.addInformation(tooltip, this.getUnlocalizedName(), i);
			}
		} else if (ModTooltips.showInfoTipKey() && !(tooltipLines == 0)) {
			ModTooltips.addKey(tooltip, ModTooltips.KEY_INFO);
		}
	}

	public Block getContainedBlock() {
		return containedBlock;
	}

	public String getContainedEntity() {
		return containedEntity;
	}

	public boolean hasCustomBehavior() {
		return hasCustomBehavior;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		boolean flag = this.containedBlock == Blocks.AIR;
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
		ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, raytraceresult);
		if (ret != null)
			return ret;
		if (raytraceresult == null) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else {
			BlockPos blockpos = raytraceresult.getBlockPos();
			if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			} else if (flag) {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			} else {
				boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
				BlockPos blockpos1 = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos
						: blockpos.offset(raytraceresult.sideHit);

				if (!playerIn.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemstack)) {
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
				} else if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1)) {
					if (playerIn instanceof EntityPlayerMP) {
						CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) playerIn, blockpos1, itemstack);
					}
					playerIn.addStat(StatList.getObjectUseStats(this));
					return !playerIn.capabilities.isCreativeMode
							? new ActionResult(EnumActionResult.SUCCESS, new ItemStack(Items.BUCKET))
							: new ActionResult(EnumActionResult.SUCCESS, itemstack);
				} else {
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
				}
			}
		}
	}

	@Override
	public boolean tryPlaceContainedLiquid(@Nullable EntityPlayer player, World worldIn, BlockPos posIn) {
		if ((this.containedBlock == null || this.containedBlock == Blocks.AIR) && this.containedEntity == null && this.hasCustomBehavior == false) {
			return false;
		} else {
			boolean doesVaporize = false;
			IBlockState iblockstate = worldIn.getBlockState(posIn);
			Material material = iblockstate.getMaterial();
			boolean flag = !material.isSolid();
			boolean flag1 = iblockstate.getBlock().isReplaceable(worldIn, posIn);
			if (!worldIn.isAirBlock(posIn) && !flag && !flag1) {
				return false;
			} else {
				if (this.containedBlock != null) {
					if (worldIn.provider.doesWaterVaporize() && doesVaporize) {
						int l = posIn.getX();
						int i = posIn.getY();
						int j = posIn.getZ();
						worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
								2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
						for (int k = 0; k < 8; ++k) {
							worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) l + Math.random(),
									(double) i + Math.random(), (double) j + Math.random(), 0.0D, 0.0D, 0.0D);
						}
					} else {
						if (!worldIn.isRemote && (flag || flag1) && !material.isLiquid()) {
							worldIn.destroyBlock(posIn, true);
						}
						Material containedMaterial = this.containedBlock.getDefaultState().getMaterial();
						SoundEvent soundevent;
						if (containedMaterial == Material.LAVA) {
							soundevent = SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
						} else if (containedMaterial == Material.WATER) {
							soundevent = SoundEvents.ITEM_BUCKET_EMPTY;
						} else {
							SoundType soundType = this.containedBlock
									.getSoundType(this.containedBlock.getDefaultState(), worldIn, posIn, null);
							soundevent = soundType.getPlaceSound();
						}
						worldIn.playSound(null, posIn, soundevent, SoundCategory.BLOCKS, 1.0F, 0.8F);
						IBlockState blockState = this.containedBlock.getDefaultState();
						if (this.containedBlock.canPlaceBlockAt(worldIn, posIn)) {
							worldIn.setBlockState(posIn, blockState, 11);
						} else {
							return false;
						}
					}
					return true;
				} else if (this.containedEntity != null) {
					if (!worldIn.isRemote) {
						Entity entity = EntityList.createEntityByIDFromName(
								new ResourceLocation(this.containedEntity), worldIn);
						if (entity != null) {
							entity.setPosition(posIn.getX() + 0.5, posIn.getY(), posIn.getZ() + 0.5);
							worldIn.spawnEntity(entity);
						}
					}
					return true;
				} else if (this.hasCustomBehavior) {
					if (!worldIn.isRemote) {
						this.tryExcecuteCustomBehavior(worldIn, posIn);
					}
					return true;
				}
			}
		}
		return false;
	}

	public void tryExcecuteCustomBehavior(World worldIn, BlockPos posIn) {
	}
}