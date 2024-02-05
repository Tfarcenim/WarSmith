package tfar.warsmith.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.ArrayList;
import java.util.List;

public class RendEnchantment extends Enchantment {
    public RendEnchantment(Rarity $$0, EnchantmentCategory $$1, EquipmentSlot... $$2) {
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
        if (target instanceof LivingEntity livingTarget && attacker.getRandom().nextInt(4) < level) {
            List<EquipmentSlot> eligible = new ArrayList<>();
            for (EquipmentSlot armorSlot : Enchantments.ARMOR_SLOTS) {
                if (!livingTarget.getItemBySlot(armorSlot).isEmpty()) {
                    eligible.add(armorSlot);
                }
            }
            if (!eligible.isEmpty()) {
                int slot = attacker.getRandom().nextInt(eligible.size());
                removeArmor(livingTarget,eligible.get(slot));
            }
        }
    }

    private void removeArmor(LivingEntity target,EquipmentSlot slot) {
        ItemStack held = target.getItemBySlot(slot);
        ItemEntity itemEntity = new ItemEntity(target.level(), target.getX(), target.getY(), target.getZ(), held);
        target.level().addFreshEntity(itemEntity);
        target.setItemSlot(slot,ItemStack.EMPTY);
    }
}
