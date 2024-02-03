package tfar.warsmith.enchantment.categories;

import net.minecraft.world.item.Item;
import tfar.warsmith.mixin.EnchantmentCategoryMixin;

import java.util.function.Predicate;

public class FireAspectEnchantmentCategory extends EnchantmentCategoryMixin {

    private Predicate<Item> cache;
    @Override
    public boolean canEnchant(Item item) {
        if (cache == null) {
            cache = ModEnchantmentCategories.createFireAspectWrapper();
        }
        return cache.test(item);
    }
}
