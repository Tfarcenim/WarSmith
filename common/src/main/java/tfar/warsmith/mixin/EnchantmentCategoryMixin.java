package tfar.warsmith.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(EnchantmentCategory.class)
public abstract class EnchantmentCategoryMixin {
        @Shadow
        public abstract boolean canEnchant(Item item);
}
