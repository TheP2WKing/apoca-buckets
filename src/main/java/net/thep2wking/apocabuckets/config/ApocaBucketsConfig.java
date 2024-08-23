package net.thep2wking.apocabuckets.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.apocabuckets.config.categories.Content;
import net.thep2wking.apocabuckets.config.categories.Desaster;

@Config(modid = ApocaBuckets.MODID, name = ApocaBuckets.MODID, category = ApocaBuckets.MODID)
public class ApocaBucketsConfig {
	@Config.Name("desaster")
    public static final Desaster DESASTER = new Desaster();

	@Config.Name("content")
    public static final Content CONTENT = new Content();

	@Mod.EventBusSubscriber
	public static class ConfigHolder {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ApocaBuckets.MODID)) {
				ConfigManager.sync(ApocaBuckets.MODID, Config.Type.INSTANCE);
			}
		}
	}
}