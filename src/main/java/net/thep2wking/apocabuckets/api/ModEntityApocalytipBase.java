package net.thep2wking.apocabuckets.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ModEntityApocalytipBase extends Entity {
	public ModEntityApocalytipBase(World worldIn) {
		super(worldIn);
		this.motionX = 0.0;
		this.motionY = 0.0;
		this.motionZ = 0.0;
	}

	@Override
	public void entityInit() {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean getIsInvulnerable() {
		return true;
	}
}