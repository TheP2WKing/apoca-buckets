package net.thep2wking.apocabuckets.util.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.thep2wking.apocabuckets.ApocaBuckets;

public class ModNetworkHandler {
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(ApocaBuckets.MODID);

    public static void registerMessages() {
        int id = 0;
        NETWORK.registerMessage(MessageStopApocalypse.Handler.class, MessageStopApocalypse.class, id++, Side.SERVER);
    }
}
