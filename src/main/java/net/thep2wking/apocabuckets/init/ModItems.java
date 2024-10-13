package net.thep2wking.apocabuckets.init;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.api.ModItemApocalypticBucketBase;
import net.thep2wking.apocabuckets.content.entity.EntityBlackHole;
import net.thep2wking.apocabuckets.content.item.ItemApocalypticBucketHelmet;
import net.thep2wking.apocabuckets.content.item.ItemDisasterRecoveryRemote;
import net.thep2wking.apocabuckets.content.itemblock.ItemBlockCreativePlaceable;
import net.thep2wking.apocabuckets.content.itemblock.ItemBlockFire;
import net.thep2wking.apocabuckets.util.ModArmorMaterial;
import net.thep2wking.oedldoedlcore.util.ModRarities;

public class ModItems {
	public static final Item WHITE_PAINT = new ItemBlockCreativePlaceable(ModBlocks.WHITE_PAINT, ModRarities.WHITE, false, 0, 0);
	public static final Item PURPLE_PAINT = new ItemBlockCreativePlaceable(ModBlocks.PURPLE_PAINT, ModRarities.WHITE, false, 0, 0);
	public static final Item FIRE = new ItemBlockFire(ApocaBuckets.MODID, "fire", ApocaBuckets.TAB, ModRarities.WHITE, false, 0, 0);
	public static final Item GREEN_FIRE = new ItemBlockCreativePlaceable(ModBlocks.GREEN_FIRE, ModRarities.WHITE, false, 0, 0);

	public static final Item APOCALYPTIC_BUCKET = new ItemApocalypticBucketHelmet(ApocaBuckets.MODID, "apocalyptic_bucket", ApocaBuckets.TAB, ModArmorMaterial.APOCALYPTIC, 0, EntityEquipmentSlot.HEAD, ModRarities.LIGHT_PURPLE, true, 0, 0);
	
	public static final Item TSUNAMI_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "tsunami_bucket", ApocaBuckets.TAB, ModBlocks.TSUNAMI, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item LAVA_TSUNAMI_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "lava_tsunami_bucket", ApocaBuckets.TAB, ModBlocks.LAVA_TSUNAMI, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item FROZEN_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "frozen_bucket", ApocaBuckets.TAB, ModBlocks.FROZEN, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item TOXIC_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "toxic_bucket", ApocaBuckets.TAB, ModBlocks.TOXIC, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item WHITE_PAINT_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "white_paint_bucket", ApocaBuckets.TAB, ModBlocks.WHITE_PAINT, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item PURPLE_PAINT_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "purple_paint_bucket", ApocaBuckets.TAB, ModBlocks.PURPLE_PAINT, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item VOID_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "void_bucket", ApocaBuckets.TAB, ModBlocks.VOID, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item FIRE_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "fire_bucket", ApocaBuckets.TAB, ModBlocks.GREEN_FIRE, ModRarities.LIGHT_PURPLE, true, 0, 0);
	public static final Item BLACK_HOLE_BUCKET = new ModItemApocalypticBucketBase(ApocaBuckets.MODID, "black_hole_bucket", ApocaBuckets.TAB, EntityBlackHole.getRegistryName(), ModRarities.LIGHT_PURPLE, true, 0, 0);
	
	public static final Item DISASTER_RECOVERY_REMOTE = new ItemDisasterRecoveryRemote(ApocaBuckets.MODID, "disaster_recovery_remote", ApocaBuckets.TAB, ModRarities.LIGHT_PURPLE, true, 0, 0);
}