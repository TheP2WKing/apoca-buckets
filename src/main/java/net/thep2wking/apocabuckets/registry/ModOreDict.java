package net.thep2wking.apocabuckets.registry;

import net.thep2wking.oedldoedlcore.util.ModLogger;
import net.thep2wking.oedldoedlcore.util.ModRecipeHelper;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.init.ModBlocks;
import net.thep2wking.apocabuckets.init.ModItems;

public class ModOreDict {
	public static void registerOreDict() {
		ModLogger.registeredOreDictLogger(ApocaBuckets.MODID);

		ModRecipeHelper.addOreDict("fire", ModItems.FIRE, 0);
		ModRecipeHelper.addOreDict("fireGreen", ModBlocks.GREEN_FIRE, 0);

		ModRecipeHelper.addOreDict("bucketApocalypticEmpty", ModItems.APOCALYPTIC_BUCKET, 0);

		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.TSUNAMI_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.LAVA_TSUNAMI_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.FROZEN_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.TOXIC_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.WHITE_PAINT_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.PURPLE_PAINT_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.VOID_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.FIRE_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.BLACK_HOLE_BUCKET, 0);
		ModRecipeHelper.addOreDict("bucketApocalyptic", ModItems.BLOOD_MOON_BUCKET, 0);
	}
}