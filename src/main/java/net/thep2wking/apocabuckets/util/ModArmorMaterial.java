package net.thep2wking.apocabuckets.util;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.thep2wking.apocabuckets.ApocaBuckets;
import net.thep2wking.oedldoedlcore.api.armor.ModArmorMaterialBase;

public class ModArmorMaterial {
	public static final ArmorMaterial APOCALYPTIC = ModArmorMaterialBase.addArmorMaterial(ApocaBuckets.MODID,
			"apocalyptic", 0, new int[] { 0, 0, 0, 4 }, 2.0f, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, Items.NETHERBRICK,
			0);
}