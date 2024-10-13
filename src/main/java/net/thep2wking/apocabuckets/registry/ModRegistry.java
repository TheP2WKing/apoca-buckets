package net.thep2wking.apocabuckets.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thep2wking.oedldoedlcore.util.ModLogger;
import net.thep2wking.oedldoedlcore.util.ModRegistryHelper;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.init.ModBlocks;
import net.thep2wking.apocabuckets.init.ModFluids;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.util.render.ModRenderer;

@Mod.EventBusSubscriber
public class ModRegistry {
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		ModRegistryHelper.registerModels(event, ApocaBuckets.MODID);
		ModRenderer.registerFluidRenderer();
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		ModLogger.registeredBlocksLogger(ApocaBuckets.MODID);

		ModRegistryHelper.registerBlock(event, ModBlocks.WHITE_PAINT);
		ModRegistryHelper.registerBlock(event, ModBlocks.PURPLE_PAINT);
		ModRegistryHelper.registerBlock(event, ModBlocks.GREEN_FIRE);

		ModRegistryHelper.registerBlock(event, ModBlocks.TSUNAMI);
		ModRegistryHelper.registerBlock(event, ModBlocks.LAVA_TSUNAMI);
		ModRegistryHelper.registerBlock(event, ModBlocks.FROZEN);
		ModRegistryHelper.registerBlock(event, ModBlocks.TOXIC);
		ModRegistryHelper.registerBlock(event, ModBlocks.VOID);
	}

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		ModLogger.registeredItemsLogger(ApocaBuckets.MODID);

		ModRegistryHelper.registerItemBlock(event, ModItems.WHITE_PAINT);
		ModRegistryHelper.registerItemBlock(event, ModItems.PURPLE_PAINT);
		ModRegistryHelper.registerItemBlock(event, ModItems.FIRE);
		ModRegistryHelper.registerItemBlock(event, ModItems.GREEN_FIRE);

		ModRegistryHelper.registerItem(event, ModItems.APOCALYPTIC_BUCKET);

		ModRegistryHelper.registerItem(event, ModItems.TSUNAMI_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.LAVA_TSUNAMI_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.FROZEN_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.TOXIC_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.WHITE_PAINT_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.PURPLE_PAINT_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.VOID_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.FIRE_BUCKET);
		ModRegistryHelper.registerItem(event, ModItems.BLACK_HOLE_BUCKET);

		ModRegistryHelper.registerItem(event, ModItems.DISASTER_RECOVERY_REMOTE);
	}

	public static void registerFluids() {
		ModLogger.registeredFluidsLogger(ApocaBuckets.MODID);
		if (ApocaBucketsConfig.CONTENT.REGISTER_FORGE_BUCKETS_FOR_FLUIDS) {
			ModRegistryHelper.registerFluid(ModFluids.TSUNAMI);
			ModRegistryHelper.registerFluid(ModFluids.LAVA_TSUNAMI);
			ModRegistryHelper.registerFluid(ModFluids.FROZEN);
			ModRegistryHelper.registerFluid(ModFluids.TOXIC);
			ModRegistryHelper.registerFluid(ModFluids.VOID);
		} else {
			ModRegistryHelper.registerFluidWithoutBucket(ModFluids.TSUNAMI);
			ModRegistryHelper.registerFluidWithoutBucket(ModFluids.LAVA_TSUNAMI);
			ModRegistryHelper.registerFluidWithoutBucket(ModFluids.FROZEN);
			ModRegistryHelper.registerFluidWithoutBucket(ModFluids.TOXIC);
			ModRegistryHelper.registerFluidWithoutBucket(ModFluids.VOID);
		}
	}
}