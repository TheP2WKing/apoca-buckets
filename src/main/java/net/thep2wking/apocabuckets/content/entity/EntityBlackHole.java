package net.thep2wking.apocabuckets.content.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;

public class EntityBlackHole extends Entity {
	public float rotationRoll;
	public byte renderId;
	public Block tileId;
	public float radius;

	public EntityBlackHole(World worldIn) {
		super(worldIn);
		this.setSize(0.98f, 0.98f);
		this.motionX = 0.0;
		this.motionY = 0.0;
		this.motionZ = 0.0;
		this.tileId = Block.getBlockById((int) (this.getEntityId() % 45));
	}

	public static String getRegistryName() {
		return ApocaBuckets.MODID + ":black_hole";
	}

	public boolean isDisabled(World world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.BLACK_HOLE
				|| !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean getIsInvulnerable() {
		return true;
	}

	@Override
	public void entityInit() {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setFloat("Radius", this.radius);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("Radius")) {
			this.radius = compound.getFloat("Radius");
		}
	}

	@Override
	public void onUpdate() {
		int newX = (int) ((int) this.posX);
		int newY = (int) ((int) this.posY);
		int newZ = (int) ((int) this.posZ);
		if (!isDisabled(world)) {
			this.radius += 0.01f;
			int radius = (int) Math.floor(this.radius);
			for (int i = (int) newX - radius; i < (int) newX + radius; ++i) {
				for (int j = (int) newY - radius; j < (int) newY + radius; ++j) {
					for (int k = (int) newZ - radius; k < (int) newZ + radius; ++k) {
						if (this.world.isRemote)
							continue;
						BlockPos pos = new BlockPos(i, j, k);
						IBlockState state = this.world.getBlockState(pos);
						Block block = state.getBlock();
						if (block != Blocks.AIR) {
							// this.world.spawnEntity(new EntityFallingBlock(this.world, i, j, k, state));
							this.world.setBlockToAir(pos);
						}
					}
				}
			}
		}

		// if (!ApocalypseWorldData.isApocalypseStopped(world)) {
		// AxisAlignedBB boundingBox = new AxisAlignedBB(
		// newX - radius, newY - radius, newZ - radius,
		// newX + radius, newY + radius, newZ + radius);
		// for (Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this,
		// boundingBox)) {
		// if (entity instanceof EntityBlackHole) {
		// continue;
		// }
		// double speed;
		// double targetX = newX;
		// double targetY = newY;
		// double targetZ = newZ;
		// if (entity.posX == targetX && entity.posY == targetY && entity.posZ ==
		// targetZ) {
		// entity.setDead();
		// }
		// if (entity.posX != targetX) {
		// speed = 0.169;
		// if (entity.posX < targetX) {
		// speed *= -1.0;
		// }
		// entity.posX = Math.abs(entity.posX) > Math.abs(speed) ? (entity.posX -=
		// speed) : targetX;
		// }
		// if (entity.posY != targetY) {
		// speed = 0.169;
		// if (entity.posY < targetY) {
		// speed *= -1.0;
		// }
		// entity.posY = Math.abs(entity.posY) > Math.abs(speed) ? (entity.posY -=
		// speed) : targetY;
		// }
		// if (entity.posZ != targetZ) {
		// speed = 0.169;
		// if (entity.posZ < targetZ) {
		// speed *= -1.0;
		// }
		// entity.posZ = Math.abs(entity.posZ) > Math.abs(speed) ? (entity.posZ -=
		// speed) : targetZ;
		// }
		// entity.setPosition(entity.posX, entity.posY, entity.posZ);
		// }
		// }
	}
}