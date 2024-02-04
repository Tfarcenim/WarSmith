package tfar.warsmith.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DashingEnchantment extends Enchantment {
    public DashingEnchantment(Rarity $$0, EnchantmentCategory $$1, EquipmentSlot... $$2) {
        super($$0, $$1, $$2);
    }


    public int getMinCost(int pEnchantmentLevel) {
        return 15;
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return super.getMinCost(pEnchantmentLevel) + 50;
    }

}
