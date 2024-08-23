package net.thep2wking.apocabuckets.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.oedldoedlcore.util.ModLogger;
import net.thep2wking.oedldoedlcore.util.ModRecipeHelper;

@Mod.EventBusSubscriber
public class ModRecipes {
	public static void registerRecipes() {
		ModLogger.registeredRecipesLogger(ApocaBuckets.MODID);

		ModRecipeHelper.addShapedRecipe(ApocaBuckets.MODID, "apocalyptic_bucket", new ItemStack(ModItems.APOCALYPTIC_BUCKET, 1, 0), "ABA", " A ", 'A', "ingotBrickNether", 'B', "netherStar");

		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "tsunami_bucket", new ItemStack(ModItems.TSUNAMI_BUCKET, 1, 0), "bucketApocalypticEmpty", "gemDiamond");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "lava_tsunami_bucket", new ItemStack(ModItems.LAVA_TSUNAMI_BUCKET, 1, 0), "bucketApocalypticEmpty", "dustBlaze");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "frozen_bucket", new ItemStack(ModItems.FROZEN_BUCKET, 1, 0), "bucketApocalypticEmpty", "blockIce");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "toxic_bucket", new ItemStack(ModItems.TOXIC_BUCKET, 1, 0), "bucketApocalypticEmpty", "dyeLime");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "white_paint_bucket", new ItemStack(ModItems.WHITE_PAINT_BUCKET, 1, 0), "bucketApocalypticEmpty", "dyeWhite");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "purple_paint_bucket", new ItemStack(ModItems.PURPLE_PAINT_BUCKET, 1, 0), "bucketApocalypticEmpty", "dyePurple");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "void_bucket", new ItemStack(ModItems.VOID_BUCKET, 1, 0), "bucketApocalypticEmpty", "obsidian");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "fire_bucket", new ItemStack(ModItems.FIRE_BUCKET, 1, 0), "bucketApocalypticEmpty", "toolFlintAndSteel");
		ModRecipeHelper.addShapelessRecipe(ApocaBuckets.MODID, "black_hole_bucket", new ItemStack(ModItems.BLACK_HOLE_BUCKET, 1, 0), "bucketApocalypticEmpty", "enderpearl");


		ModRecipeHelper.addShapedRecipe(ApocaBuckets.MODID, "desaster_recovery_remote", new ItemStack(ModItems.DESASTER_RECOVERY_REMOTE, 1, 0), " AB", "ACA", "DA ", 'A', "ingotBrickNether", 'B', "netherStar", 'C', "commandBlock", 'D', "bucketApocalypticEmpty");
	}
}