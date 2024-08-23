package net.thep2wking.apocabuckets.util.render;

import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.content.entity.EntityBlackHole;
import net.thep2wking.apocabuckets.init.ModBlocks;
import net.thep2wking.oedldoedlcore.util.ModFluidUtil;
import net.thep2wking.oedldoedlcore.util.ModRenderHelper;

public class ModRenderer {
	public static void registerFluidRenderer() {
		ModFluidUtil.addRenderForFluid(ApocaBuckets.MODID, "tsunami", ModBlocks.TSUNAMI);
		ModFluidUtil.addRenderForFluid(ApocaBuckets.MODID, "lava_tsunami", ModBlocks.LAVA_TSUNAMI);
		ModFluidUtil.addRenderForFluid(ApocaBuckets.MODID, "frozen", ModBlocks.FROZEN);
		ModFluidUtil.addRenderForFluid(ApocaBuckets.MODID, "toxic", ModBlocks.TOXIC);
		ModFluidUtil.addRenderForFluid(ApocaBuckets.MODID, "void", ModBlocks.VOID);
	}

	public static void registerEntityRender() {
		ModRenderHelper.addEntityRender(EntityBlackHole.class, RenderEntityBlackHole.FACTORY);
	}
}