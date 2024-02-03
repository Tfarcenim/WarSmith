package tfar.warsmith.enchantment.categories;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import tfar.warsmith.mixin.EnchantmentCategoryMixin;

public class ModEnchantmentCategory extends EnchantmentCategoryMixin {

    protected final TagKey<Item> valid;

    public ModEnchantmentCategory(TagKey<Item> valid) {
        this.valid = valid;
    }

    @Override
    public boolean canEnchant(Item item) {
        return item.builtInRegistryHolder().is(valid);
    }
}
