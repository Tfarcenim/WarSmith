package tfar.warsmith.enchantment.categories;

import net.minecraft.world.item.Item;
import tfar.warsmith.mixin.EnchantmentCategoryMixin;

public class FireAspectEnchantmentCategory extends EnchantmentCategoryMixin {
    @Override
    public boolean canEnchant(Item item) {
        return ModEnchantmentCategories.createFireAspectWrapper().test(item);
    }
}
