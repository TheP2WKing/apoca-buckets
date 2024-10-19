package net.thep2wking.apocabuckets.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.oedldoedlcore.util.ModLogger;
import net.thep2wking.oedldoedlcore.util.ModRecipeHelper;

@Mod.EventBusSubscriber
public class ModRecipes {
	public static void registerRecipes() {
		ModLogger.registeredRecipesLogger(ApocaBuckets.MODID);

		ModRecipeHelper.addShapedRecipe(ApocaBuckets.MODID, "apocalyptic_bucket",
				new ItemStack(ModItems.APOCALYPTIC_BUCKET, 1, 0), "ABA", " A ", 'A', "ingotBrickNether", 'B',
				"netherStar");

		if (ApocaBucketsConfig.DESASTER.TSUNAMI) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "tsunami_bucket",
					new ItemStack(ModItems.TSUNAMI_BUCKET, 1, 0), "bucketApocalypticEmpty", "gemDiamond");
		}
		if (ApocaBucketsConfig.DESASTER.LAVA_TSUNAMI) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "lava_tsunami_bucket",
					new ItemStack(ModItems.LAVA_TSUNAMI_BUCKET, 1, 0), "bucketApocalypticEmpty", "dustBlaze");
		}
		if (ApocaBucketsConfig.DESASTER.FROZEN) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "frozen_bucket",
					new ItemStack(ModItems.FROZEN_BUCKET, 1, 0), "bucketApocalypticEmpty", "blockIce");
		}
		if (ApocaBucketsConfig.DESASTER.TOXIC) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "toxic_bucket",
					new ItemStack(ModItems.TOXIC_BUCKET, 1, 0), "bucketApocalypticEmpty", "dyeLime");
		}
		if (ApocaBucketsConfig.DESASTER.WHITE_PAINT) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "white_paint_bucket",
					new ItemStack(ModItems.WHITE_PAINT_BUCKET, 1, 0), "bucketApocalypticEmpty", "dyeWhite");
		}
		if (ApocaBucketsConfig.DESASTER.PURPLE_PAINT) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "purple_paint_bucket",
					new ItemStack(ModItems.PURPLE_PAINT_BUCKET, 1, 0), "bucketApocalypticEmpty", "dyePurple");
		}
		if (ApocaBucketsConfig.DESASTER.VOID) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "void_bucket",
					new ItemStack(ModItems.VOID_BUCKET, 1, 0), "bucketApocalypticEmpty", "obsidian");
		}
		if (ApocaBucketsConfig.DESASTER.FIRE) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "fire_bucket",
					new ItemStack(ModItems.FIRE_BUCKET, 1, 0), "bucketApocalypticEmpty", "toolFlintAndSteel");
		}
		if (ApocaBucketsConfig.DESASTER.BLACK_HOLE) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "black_hole_bucket",
					new ItemStack(ModItems.BLACK_HOLE_BUCKET, 1, 0), "bucketApocalypticEmpty", "enderpearl");
		}
		if (ApocaBucketsConfig.DESASTER.BLOOD_MOON) {
			ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "blood_moon_bucket",
					new ItemStack(ModItems.BLOOD_MOON_BUCKET, 1, 0), "bucketApocalypticEmpty", "dustRedstone");
		}

		if (ApocaBucketsConfig.CONTENT.DESASTER_RECOVERY_REMOTE_USEABLE_IN_SURVIVAL) {
			ModRecipeHelper.addShapedRecipe(ApocaBuckets.MODID, "disaster_recovery_remote",
					new ItemStack(ModItems.DISASTER_RECOVERY_REMOTE, 1, 0), " AB", "ACA", "DA ", 'A',
					"ingotBrickNether", 'B', "netherStar", 'C', "commandBlock", 'D', "bucketApocalypticEmpty");
		}
	}
}