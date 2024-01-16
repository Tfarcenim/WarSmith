package tfar.warsmith.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import tfar.warsmith.duck.EntityDuck;

public class AmaterasuEnchantment extends Enchantment {
    public AmaterasuEnchantment(Rarity $$0, EnchantmentCategory $$1, EquipmentSlot... $$2) {
        super($$0, $$1, $$2);
    }

    public int getMinCost(int pEnchantmentLevel) {
        return 10 + 20 * (pEnchantmentLevel - 1);
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return super.getMinCost(pEnchantmentLevel) + 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        ((EntityDuck)target).setSecondsOnSoulFire(level * 4);
    }
}
