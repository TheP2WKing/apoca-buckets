package net.thep2wking.apocabuckets.content.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thep2wking.apocabuckets.api.ModItemApocalypticBucketBase;
import net.thep2wking.apocabuckets.util.event.EventBloodMoon;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;

public class ItemBloodMoonBucket extends ModItemApocalypticBucketBase {
	public ItemBloodMoonBucket(String modid, String name, CreativeTabs tab, EnumRarity rarity,
			boolean hasEffect, int tooltipLines, int annotationLines) {
		super(modid, name, tab, rarity, hasEffect, tooltipLines, annotationLines);
	}

	@Override
	public void tryExcecuteCustomBehavior(World worldIn, BlockPos posIn) {
		super.tryExcecuteCustomBehavior(worldIn, posIn);
		if (ModWorldSavedData.isBloodMoon(worldIn)) {
			return;
		}
		EventBloodMoon.startBloodMoon(worldIn);
	}
}