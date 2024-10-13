package net.thep2wking.apocabuckets.integration.top;

import java.util.function.Function;

import javax.annotation.Nullable;

import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class TOPCompatibility {
    private static boolean registered;

    public static void register() {
        if (registered)
            return;
        registered = true;
        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe",
                "net.thep2wking.apocabuckets.integration.top.TOPCompatibility$GetTheOneProbe");
    }

    public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {
        public static ITheOneProbe probe;

        @Nullable
        @Override
        public Void apply(ITheOneProbe theOneProbe) {
            probe = theOneProbe;
            probe.registerBlockDisplayOverride(new FluidBlockInfoProvider());
            return null;
        }
    }
}