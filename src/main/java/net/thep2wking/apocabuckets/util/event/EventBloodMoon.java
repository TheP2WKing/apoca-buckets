package net.thep2wking.apocabuckets.util.event;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;

public class EventBloodMoon {
	public static final List<String> MOB_LIST = Arrays.asList("minecraft:zombie", "minecraft:skeleton",
			"minecraft:spider", "minecraft:creeper", "minecraft:witch", "minecraft:enderman");
	public static final List<Potion> POTION_LIST = Arrays.asList(MobEffects.STRENGTH, MobEffects.SPEED,
			MobEffects.JUMP_BOOST, MobEffects.RESISTANCE);

	public static void startBloodMoon(World world) {
		if (world instanceof WorldServer) {
			WorldServer worldServer = (WorldServer) world;
			ModWorldSavedData apocalypseData = ModWorldSavedData.get(worldServer);
			if (apocalypseData != null) {
				apocalypseData.setBloodMoon(true);
				apocalypseData.setBloodMoonTicks(0);
			}
			((WorldServer) world).setWorldTime(18000);
		}
	}

	public static void stopBloodMoon(World world) {
		if (world instanceof WorldServer) {
			WorldServer worldServer = (WorldServer) world;
			ModWorldSavedData apocalypseData = ModWorldSavedData.get(worldServer);
			if (apocalypseData != null) {
				apocalypseData.setBloodMoon(false);
				apocalypseData.setBloodMoonTicks(0);
			}
		}
	}

	public boolean isDisabled(World world) {
		return ModWorldSavedData.isApocalypseStopped(world) || !ApocaBucketsConfig.DESASTER.BLOOD_MOON
				|| !ApocaBucketsConfig.DESASTER.ENABLE_ALL_DESASTERS;
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		World world = event.world;
		if (!(world instanceof WorldServer) || !ModWorldSavedData.isBloodMoon(world)) {
			return;
		}
		WorldServer worldServer = (WorldServer) world;
		if (isDisabled(world) || world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			return;
		}
		if (world.isDaytime()) {
			world.setWorldTime(18000);
		}
		int elapsedTicks = ModWorldSavedData.getBloodMoonTicks(worldServer);
		++elapsedTicks;
		ModWorldSavedData apocalypseData = ModWorldSavedData.get(worldServer);
		if (apocalypseData != null) {
			apocalypseData.setBloodMoonTicks(elapsedTicks);
		}
		if (!worldServer.isDaytime() && elapsedTicks % 10 == 0) {
			Random r = new Random();
			int numMobsToSpawn = elapsedTicks / 200;
			for (EntityPlayer player : world.playerEntities) {
				for (int i = 0; i < numMobsToSpawn; i++) {
					int xOffset = r.nextInt(96) - 48;
					int zOffset = r.nextInt(96) - 48;
					if (Math.abs(xOffset) < 16 && Math.abs(zOffset) < 16) {
						continue;
					}
					BlockPos spawnPos = new BlockPos(player.posX + xOffset, player.posY, player.posZ + zOffset);
					spawnPos = world.getTopSolidOrLiquidBlock(spawnPos);
					String mobRegistryName = MOB_LIST.get(r.nextInt(MOB_LIST.size()));
					EntityLiving mob = (EntityLiving) EntityList
							.createEntityByIDFromName(new ResourceLocation(mobRegistryName), world);
					Potion potion = POTION_LIST.get(r.nextInt(POTION_LIST.size()));
					if (mob != null) {
						mob.setPosition(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
						if (r.nextInt(4) == 0) {
							mob.addPotionEffect(new PotionEffect(potion, 1000000, r.nextInt(2), false, false));
						}
						world.spawnEntity(mob);
					}
				}
			}
		}
	}
}