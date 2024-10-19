package net.thep2wking.apocabuckets.content.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.thep2wking.apocabuckets.api.ModEntityApocalytipBase;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.util.event.EventBloodMoon;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.oedldoedlcore.api.item.ModItemBase;
import net.thep2wking.oedldoedlcore.util.ModTooltips;

public class ItemDisasterRecoveryRemote extends ModItemBase {
    public ItemDisasterRecoveryRemote(String modid, String name, CreativeTabs tab, EnumRarity rarity, boolean hasEffect,
            int tooltipLines, int annotationLines) {
        super(modid, name, tab, rarity, hasEffect, tooltipLines, annotationLines);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (ApocaBucketsConfig.CONTENT.DESASTER_RECOVERY_REMOTE_CONTROLS_APOCALYPSES) {
            if (!world.isRemote && world != null && world instanceof WorldServer) {
                if (ApocaBucketsConfig.CONTENT.DESASTER_RECOVERY_REMOTE_USEABLE_IN_SURVIVAL
                        || player.capabilities.isCreativeMode) {
                    WorldServer worldServer = (WorldServer) world;
                    ModWorldSavedData apocalypseData = ModWorldSavedData.get(worldServer);
                    boolean isStopped = apocalypseData.isStopApocalypse();
                    if (player.isSneaking()) {
                        ModTooltips.sendItemInfoChatComponent(player, stack, 1, TextFormatting.LIGHT_PURPLE);
                        EventBloodMoon.stopBloodMoon(worldServer);
                        List<ModEntityApocalytipBase> entities = worldServer.getEntities(ModEntityApocalytipBase.class,
                                entity -> true);
                        for (ModEntityApocalytipBase entity : entities) {
                            entity.setDead();
                        }
                    } else {
                        apocalypseData.toggleStopApocalypse();
                        if (!isStopped) {
                            ModTooltips.sendItemInfoChatComponent(player, stack, 2, TextFormatting.LIGHT_PURPLE);
                        } else {
                            ModTooltips.sendItemInfoChatComponent(player, stack, 3, TextFormatting.LIGHT_PURPLE);
                        }
                    }
                } else {
                    ModTooltips.sendItemInfoChatComponent(player, stack, 4, TextFormatting.RED);
                }
            }
            player.swingArm(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        if (!world.isRemote) {
            ModTooltips.sendItemInfoChatComponent(player, stack, 4, TextFormatting.RED);
        }
        player.swingArm(hand);
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }
}