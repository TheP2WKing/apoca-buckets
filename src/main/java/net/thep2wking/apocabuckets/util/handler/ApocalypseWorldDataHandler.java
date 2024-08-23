package net.thep2wking.apocabuckets.util.handler;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ApocalypseWorldDataHandler {
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (!event.getWorld().isRemote) {
            ModWorldSavedData.get(event.getWorld());
        }
    }

    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event) {
        if (!event.getWorld().isRemote) {
            ModWorldSavedData apocalypseData = ModWorldSavedData.get(event.getWorld());
            if (apocalypseData != null) {
                apocalypseData.markDirty();
            }
        }
    }
}