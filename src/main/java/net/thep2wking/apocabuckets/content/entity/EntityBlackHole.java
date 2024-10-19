package net.thep2wking.apocabuckets.content.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.api.ModEntityApocalytipBase;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;

public class EntityBlackHole extends ModEntityApocalytipBase {
	public float rotationRoll;
	public byte renderId;
	public Block tileId;
	public float radius;

	public EntityBlackHole(World worldIn) {
		super(worldIn);
		this.setSize(0.98f, 0.98f);
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
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setFloat("Radius", this.radius);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("Radius")) {
			this.radius = compound.getFloat("Radius");
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
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
							this.world.setBlockToAir(pos);
						}
					}
				}
			}
		}
	}
}