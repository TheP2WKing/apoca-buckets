package net.thep2wking.apocabuckets.integration;

import mcjty.theoneprobe.Tools;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IBlockDisplayOverride;
import mcjty.theoneprobe.api.IProbeConfig;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import mcjty.theoneprobe.api.TextStyleClass;
import mcjty.theoneprobe.apiimpl.ProbeConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.thep2wking.apocabuckets.api.ModBlockFluidApocalypticBase;
import net.thep2wking.apocabuckets.init.ModItems;

public class FluidBlockInfoProvider implements IBlockDisplayOverride {
	@Override
	public boolean overrideStandardInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player,
			World world, IBlockState blockState, IProbeHitData data) {
		if (blockState.getBlock() instanceof ModBlockFluidApocalypticBase) {
			Block block = blockState.getBlock();
			String modid = Tools.getModName(block);
			ItemStack pickBlock = new ItemStack(ModItems.APOCALYPTIC_BUCKET);
			IProbeConfig config = new ProbeConfig();
			if (block instanceof BlockFluidBase || block instanceof BlockLiquid) {
				Fluid fluid = FluidRegistry.lookupFluidForBlock(block);
				if (fluid != null) {
					ModBlockFluidApocalypticBase fluidBlock = (ModBlockFluidApocalypticBase) block;
					ItemStack bucketStack = fluidBlock.getBucket();
					IProbeInfo horizontal = probeInfo.horizontal();
					horizontal.item(bucketStack);
					horizontal.vertical().text(TextStyleClass.NAME + bucketStack.getDisplayName())
							.text(TextStyleClass.MODNAME + modid);
					return true;
				}
			}
			if (!pickBlock.isEmpty()) {
				if (Tools.show(mode, config.getShowModName())) {
					probeInfo.horizontal().item(pickBlock).vertical().itemLabel(pickBlock)
							.text(TextStyleClass.MODNAME + modid);
				} else {
					probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
							.item(pickBlock).itemLabel(pickBlock);
				}
			} else if (Tools.show(mode, config.getShowModName())) {
				probeInfo.vertical().text(TextStyleClass.NAME + getBlockUnlocalizedName(block))
						.text(TextStyleClass.MODNAME + modid);
			} else {
				probeInfo.vertical().text(TextStyleClass.NAME + getBlockUnlocalizedName(block));
			}
			return true;
		}
		return false;
	}

	private static String getBlockUnlocalizedName(Block block) {
		return "{*" + block.getUnlocalizedName() + ".name" + "*}";
	}
}