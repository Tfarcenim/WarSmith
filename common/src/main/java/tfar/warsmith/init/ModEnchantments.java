package tfar.warsmith.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import tfar.warsmith.enchantment.AmaterasuEnchantment;
import tfar.warsmith.enchantment.SneakAttackEnchantment;
import tfar.warsmith.enchantment.categories.ModEnchantmentCategories;
import tfar.warsmith.enchantment.SleightOfHandEnchantment;

public class ModEnchantments {

    public static final Enchantment AMATERASU = new AmaterasuEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND);
    public static final Enchantment SLEIGHT_OF_HAND = new SleightOfHandEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND);
    public static final Enchantment SNEAK_ATTACK = new SneakAttackEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND);

}
