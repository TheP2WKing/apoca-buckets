package net.thep2wking.apocabuckets.registry;

import net.minecraftforge.fml.common.Mod;
import net.thep2wking.apocabuckets.ApocaBuckets;

import net.thep2wking.oedldoedlcore.util.ModLogger;

@Mod.EventBusSubscriber
public class ModRecipes {
	public static void registerRecipes() {
		ModLogger.registeredRecipesLogger(ApocaBuckets.MODID);
	}
}