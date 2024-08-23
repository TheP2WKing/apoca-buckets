package net.thep2wking.apocabuckets.content.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;
import net.thep2wking.apocabuckets.util.network.MessageStopApocalypse;
import net.thep2wking.apocabuckets.util.network.ModNetworkHandler;
import net.thep2wking.oedldoedlcore.api.item.ModItemBase;

public class ItemDesasterRecoveryRemote extends ModItemBase {
    public ItemDesasterRecoveryRemote(String modid, String name, CreativeTabs tab, EnumRarity rarity, boolean hasEffect,
            int tooltipLines, int annotationLines) {
        super(modid, name, tab, rarity, hasEffect, tooltipLines, annotationLines);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (ApocaBucketsConfig.CONTENT.DESASTER_RECOVERY_REMOTE_CONTROLS_APOCALYPSES) {
            if (!world.isRemote) {
                ModNetworkHandler.NETWORK.sendToServer(new MessageStopApocalypse(true));
                ModWorldSavedData apocalypseData = ModWorldSavedData.get(world);
                boolean isStopped = apocalypseData.isStopApocalypse();
                if (ApocaBucketsConfig.CONTENT.DESASTER_RECOVERY_REMOTE_USEABLE_IN_SURVIVAL
                        || player.capabilities.isCreativeMode) {
                    if (!isStopped) {
                        player.sendMessage(new TextComponentTranslation(this.getUnlocalizedName() + ".tip1"));
                    } else {
                        player.sendMessage(new TextComponentTranslation(this.getUnlocalizedName() + ".tip2"));
                    }
                } else {
                    player.sendMessage(new TextComponentTranslation(this.getUnlocalizedName() + ".tip3"));
                }
            }
            player.swingArm(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        if (!world.isRemote) {
            player.sendMessage(new TextComponentTranslation(this.getUnlocalizedName() + ".tip3"));
        }
        player.swingArm(hand);
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }
}