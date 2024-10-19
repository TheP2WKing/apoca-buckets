package net.thep2wking.apocabuckets.content.item;

import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.AttributeModifierOperation;
import net.thep2wking.apocabuckets.config.ApocaBucketsConfig;
import net.thep2wking.oedldoedlcore.api.armor.ModItemArmorBase;
import net.thep2wking.oedldoedlcore.util.ModArmorHelper;
import net.thep2wking.oedldoedlcore.util.ModReferences;

public class ItemApocalypticBucketHelmet extends ModItemArmorBase {
	public ItemApocalypticBucketHelmet(String modid, String name, CreativeTabs tab, ArmorMaterial material,
			int renderIndex, EntityEquipmentSlot slot, EnumRarity rarity, boolean hasEffect, int tooltipLines,
			int annotationLines) {
		super(modid, name, tab, material, renderIndex, slot, rarity, hasEffect, tooltipLines, annotationLines);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
		if (slot == armorType && ApocaBucketsConfig.CONTENT.APOCALYPTIC_BUCKET_HELMET_EFFECTS) {
			ModArmorHelper.addFullArmorModifier(multimap, stack, slot, SharedMonsterAttributes.KNOCKBACK_RESISTANCE,
					ModReferences.ATTRIBUTE_KNOCKBACK_RESISTANCE, 0.5, AttributeModifierOperation.ADD);
		}
		return multimap;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (ApocaBucketsConfig.CONTENT.APOCALYPTIC_BUCKET_HELMET_EFFECTS) {
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 3, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 200, 3, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 200, 0, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
			player.removeActivePotionEffect(MobEffects.BLINDNESS);
			player.removeActivePotionEffect(MobEffects.SLOWNESS);
			player.removeActivePotionEffect(MobEffects.HUNGER);
			player.removeActivePotionEffect(MobEffects.NAUSEA);
			player.extinguish();
			player.setAir(300);
		}
	}
}