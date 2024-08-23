package net.thep2wking.apocabuckets;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thep2wking.oedldoedlcore.api.tab.ModCreativeTabBase;
import net.thep2wking.oedldoedlcore.util.ModLogInUtil;
import net.thep2wking.oedldoedlcore.util.ModLogger;
import net.thep2wking.oedldoedlcore.util.ModReferences;
import net.thep2wking.apocabuckets.registry.ModRecipes;
import net.thep2wking.apocabuckets.init.ModEntities;
import net.thep2wking.apocabuckets.init.ModItems;
import net.thep2wking.apocabuckets.registry.ModOreDict;
import net.thep2wking.apocabuckets.registry.ModRegistry;
import net.thep2wking.apocabuckets.util.handler.ApocalypseWorldDataHandler;
import net.thep2wking.apocabuckets.util.network.ModNetworkHandler;
import net.thep2wking.apocabuckets.util.proxy.CommonProxy;


@Mod(modid = ApocaBuckets.MODID, name = ApocaBuckets.NAME, version = ApocaBuckets.VERSION, dependencies = ApocaBuckets.DEPENDENCIES)
public class ApocaBuckets {
    public static final String MODID = "apocabuckets";
    public static final String PREFIX = MODID + ":";
    public static final String MC_VERSION = "1.12.2";
    public static final String NAME = "Apoca Buckets Reloaded";
    public static final String VERSION = MC_VERSION + "-" + "1.0.0";
    public static final String DEPENDENCIES = "required-after:forge@[14.23.5.2847,);required-after:oedldoedlcore@[1.12.2-4.2.0,);" + ModReferences.OEDLDOEDL_MODS_FIRST;
    public static final String CLIENT_PROXY_CLASS = "net.thep2wking.apocabuckets.util.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "net.thep2wking.apocabuckets.util.proxy.ServerProxy";

    @Mod.Instance(MODID)
    public static ApocaBuckets INSTANCE;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy PROXY;

    public static final CreativeTabs TAB = new ModCreativeTabBase(MODID, ModReferences.CREATIVE_TAB_LIGHT, false,
            true) {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.APOCALYPTIC_BUCKET);
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModLogger.preInitLogger(MODID);
        ModEntities.registerEntities();
        ModRegistry.registerFluids();
        ModNetworkHandler.registerMessages();
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModLogger.initLogger(MODID);
        ModOreDict.registerOreDict();
        ModRecipes.registerRecipes();
        MinecraftForge.EVENT_BUS.register(new ApocalypseWorldDataHandler());
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModLogger.postInitLogger(MODID);
        PROXY.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        ModLogger.loadCompleteLogger(MODID, VERSION);
    }

    @Mod.EventBusSubscriber
    public static class ModJoinMessage {
        @SubscribeEvent
        public static void addJoinMessage(PlayerLoggedInEvent event) {
            ModLogInUtil.addJoinMessage(event, NAME, "apoca-buckets-reloaded", VERSION, true);
        }
    }
}