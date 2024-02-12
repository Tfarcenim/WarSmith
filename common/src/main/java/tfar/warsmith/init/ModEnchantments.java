package tfar.warsmith.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import tfar.warsmith.WarSmith;
import tfar.warsmith.duck.EntityDuck;
import tfar.warsmith.enchantment.SimpleEnchantment;
import tfar.warsmith.enchantment.categories.ModEnchantmentCategories;

import java.util.ArrayList;
import java.util.List;

public class ModEnchantments {

    public static final Enchantment AMATERASU = SimpleEnchantment.Builder
            .builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND)
            .maxLevel(2)
            .minCost(level -> 20 * level - 10)
            .range(level -> 50)
            .postAttack((attacker, target, level) -> ((EntityDuck)target).setSecondsOnSoulFire(level * 4))
            .build();
    public static final Enchantment SLEIGHT_OF_HAND = SimpleEnchantment.Builder
            .builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.SLEIGHT_OF_HAND, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment SNEAK_ATTACK = SimpleEnchantment.Builder
            .builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.KATANA, EquipmentSlot.MAINHAND)
            .maxLevel(3)
            .minCost(level -> level * 8 - 3)
            .range(level -> 50)
            .build();
    public static final Enchantment THIEVING_CHAIN = SimpleEnchantment.Builder
            .builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.KUSARIGAMA, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment OPPORTUNISTIC_STRIKE = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.SAI, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment TOXIC_ADVANTAGE = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.SAI, EquipmentSlot.MAINHAND)
            .maxLevel(3)
            .minCost(level -> level * 8 - 3)
            .range(level -> 50)
            .postAttack((attacker, target, level) -> {
                if (target instanceof LivingEntity livingTarget) {
                    int seconds = 4 * (1 + level / 2);//4,8,8,12,12... etc
                    int amplifier = (level - 1) / 2;//0,0,1,1,2,2,etc
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON,seconds * 20,amplifier));
                }
            })
            .build();
    public static final Enchantment HALT = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.HALBERD, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .postAttack((attacker, target, level) -> {
                if (target instanceof LivingEntity livingTarget) {
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,level * 40,6));//duration, amplifier
                }
            })
            .build();
    public static final Enchantment GOLIATH_FELLER = SimpleEnchantment.Builder.builder
            (Enchantment.Rarity.RARE, ModEnchantmentCategories.HALBERD, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment DEVASTATING_SLASH = SimpleEnchantment.Builder
            .builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.HALBERD, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .postAttack((attacker, target, level) -> {
                if (target instanceof LivingEntity livingTarget && livingTarget.getRandom().nextDouble() < .6) {
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,level * 100,0));//duration, amplifier
                }
            })
            .build();
    public static final Enchantment DEADLY_SWEEP = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE,ModEnchantmentCategories.BATTLEAXE,EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment PIERCING_DASH = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE,ModEnchantmentCategories.RAPIER,EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment CRITICAL_STRIKE = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE,ModEnchantmentCategories.RAPIER,EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment DASHING = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE,ModEnchantmentCategories.RAPIER,EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment ARMOR_PIERCING = SimpleEnchantment.Builder
            .builder(Enchantment.Rarity.RARE, ModEnchantmentCategories.RAPIER, EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .build();
    public static final Enchantment REND = SimpleEnchantment.Builder.builder(Enchantment.Rarity.RARE,ModEnchantmentCategories.MACE,EquipmentSlot.MAINHAND)
            .minCost(level -> 15)
            .range(level -> 50)
            .postAttack((attacker, target, level) -> {
                if (target instanceof LivingEntity livingTarget && attacker.getRandom().nextInt(4) < level) {
                    List<EquipmentSlot> eligible = new ArrayList<>();
                    for (EquipmentSlot armorSlot : Enchantments.ARMOR_SLOTS) {
                        if (!livingTarget.getItemBySlot(armorSlot).isEmpty()) {
                            eligible.add(armorSlot);
                        }
                    }
                    if (!eligible.isEmpty()) {
                        int slot = attacker.getRandom().nextInt(eligible.size());
                        WarSmith.removeArmor(livingTarget,eligible.get(slot));
                    }
                }
            })
            .build();

}
