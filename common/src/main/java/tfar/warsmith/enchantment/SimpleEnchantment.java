package tfar.warsmith.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.IntUnaryOperator;

public class SimpleEnchantment extends Enchantment {
    protected final int maxLevel;
    protected final IntUnaryOperator minCost;
    protected final IntUnaryOperator range;
    private final PostAttack postAttack;

    protected SimpleEnchantment(Rarity $$0, EnchantmentCategory $$1, EquipmentSlot[] $$2, int maxLevel,
                                IntUnaryOperator minCost, IntUnaryOperator range, PostAttack postAttack) {
        super($$0, $$1, $$2);
        this.maxLevel = maxLevel;
        this.minCost = minCost;
        this.range = range;
        this.postAttack = postAttack;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getMinCost(int level) {
        return minCost.applyAsInt(level);
    }

    @Override
    public int getMaxCost(int level) {
        return minCost.applyAsInt(level) + range.applyAsInt(level);
    }

    @Override
    public void doPostAttack(LivingEntity $$0, Entity $$1, int $$2) {
        postAttack.doPostAttack($$0, $$1, $$2);
    }

    public static class Builder {
        final Rarity rarity;
        final EnchantmentCategory category;
        final EquipmentSlot[] slots;
        int maxLevel = 1;

        IntUnaryOperator minCost = level -> 1 + level * 10;
        IntUnaryOperator range = level -> 5;
        PostAttack postAttack = (attacker, target, level) -> {};

        public Builder(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
            this.rarity = rarity;
            this.category = category;
            this.slots = slots;
        }

        public static Builder builder(Rarity rarity,EnchantmentCategory category,EquipmentSlot... slots) {
            return new Builder(rarity,category,slots);
        }

        public Builder maxLevel(int level) {
            this.maxLevel = level;
            return this;
        }

        public Builder minCost(IntUnaryOperator minCost) {
            this.minCost = minCost;
            return this;
        }

        public Builder range(IntUnaryOperator range) {
            this.range = range;
            return this;
        }

        public Builder postAttack(PostAttack postAttack) {
            this.postAttack = postAttack;
            return this;
        }

        public SimpleEnchantment build() {
            return new SimpleEnchantment(rarity,category,slots,maxLevel,minCost,range,postAttack);
        }
    }

    @FunctionalInterface
    public interface PostAttack {
        void doPostAttack(LivingEntity attacker, Entity target, int level);
    }
}
