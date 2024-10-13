package net.thep2wking.apocabuckets.content.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.Fluid;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.oedldoedlcore.util.ModPotionUtil;

public class BlockFluidToxic extends BlockFluidTsunami {
	public BlockFluidToxic(String modid, String name, Fluid fluid, int fogColor, Material material, MapColor mapColor) {
		super(modid, name, fluid, fogColor, material, mapColor);
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(ModItems.TOXIC_BUCKET);
	}

	@Override
	public boolean isDisabled(WorldServer world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.TOXIC
				|| !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		if (entityIn instanceof EntityZombie) {
			EntityZombie zombie = (EntityZombie) entityIn;
			ItemStack diamondHelmet = new ItemStack(Items.DIAMOND_HELMET);
			zombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, diamondHelmet);
		} else if (entityIn instanceof EntityPlayer) {
			ModPotionUtil.addEffectNotInCreativeMode(entityIn, MobEffects.HUNGER, 20 * 60 * 10, 99);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		// if (isDisabled(world)) {
		// 	return;
		// }
		AxisAlignedBB boundingBox = new AxisAlignedBB(pos).grow(1);
		List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, boundingBox);
		for (Entity entity : entities) {
			if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)
					&& !(entity instanceof EntityZombie)) {
				entity.attackEntityFrom(DamageSource.MAGIC, 10.0F);
			}
		}
	}
}