package net.thep2wking.apocabuckets.init;

import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.content.entity.EntityBlackHole;
import net.thep2wking.oedldoedlcore.util.ModRegistryHelper;

public class ModEntities {
	public static void registerEntities() {
		int id = 0; 
		
		ModRegistryHelper.registerEntity(ApocaBuckets.MODID, "black_hole", ApocaBuckets.INSTANCE, id++, EntityBlackHole.class, 640, 10, true);
	}
}