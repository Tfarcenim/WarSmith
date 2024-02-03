package tfar.warsmith.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DevastatingSlashEnchantment extends Enchantment {
    public DevastatingSlashEnchantment(Rarity $$0, EnchantmentCategory $$1, EquipmentSlot... $$2) {
        super($$0, $$1, $$2);
    }

    public int getMinCost(int pEnchantmentLevel) {
        return 15;
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return super.getMinCost(pEnchantmentLevel) + 50;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        super.doPostAttack(attacker, target, level);
        if (target instanceof LivingEntity livingTarget && livingTarget.getRandom().nextDouble() < .6) {
            livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,level * 100,0));//duration, amplifier
        }
    }
}
