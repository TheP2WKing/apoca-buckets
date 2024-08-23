package net.thep2wking.apocabuckets.content.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thep2wking.oedldoedlcore.api.item.ModItemBlockBase;

public class ItemBlockCreativePlaceable extends ModItemBlockBase {
	public ItemBlockCreativePlaceable(Block block, EnumRarity rarity, boolean hasEffect, int tooltipLines,
			int annotationLines) {
		super(block, rarity, hasEffect, tooltipLines, annotationLines);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.capabilities.isCreativeMode) {
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		} else {
			return EnumActionResult.FAIL;
		}
	}
}