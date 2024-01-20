package tfar.warsmith.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import tfar.warsmith.enchantment.*;
import tfar.warsmith.enchantment.categories.ModEnchantmentCategories;

public class ModEnchantments {

    public static final Enchantment AMATERASU = new AmaterasuEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND);
    public static final Enchantment SLEIGHT_OF_HAND = new SleightOfHandEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND);
    public static final Enchantment SNEAK_ATTACK = new SneakAttackEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND);
    public static final Enchantment THIEVING_CHAIN = new ThievingChainEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.KUSARIGAMA, EquipmentSlot.MAINHAND);
    public static final Enchantment OPPORTUNISTIC_STRIKE = new OpportunisticStrikeEnchantment(Enchantment.Rarity.RARE, ModEnchantmentCategories.SAI, EquipmentSlot.MAINHAND);

}
