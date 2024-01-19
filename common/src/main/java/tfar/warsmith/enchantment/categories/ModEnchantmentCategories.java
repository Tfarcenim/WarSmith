package tfar.warsmith.enchantment.categories;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import tfar.warsmith.platform.Services;
import tfar.warsmith.tags.ModItemTags;

import java.util.function.Predicate;

public class ModEnchantmentCategories {
    public static final EnchantmentCategory KATANA = Services.PLATFORM.create("KATANA", ModItemTags.KATANAS);
    public static final EnchantmentCategory KUSARIGAMA = Services.PLATFORM.create("KUSARIGAMA", ModItemTags.KUSARIGAMAS);
    public static final EnchantmentCategory FIRE_ASPECT = Services.PLATFORM.create("FIRE_ASPECT", createFireAspectWrapper());

    public static Predicate<Item> createFireAspectWrapper() {
        return  item -> item.builtInRegistryHolder().is(ModItemTags.KUSARIGAMAS) || item instanceof SwordItem;
    }

}
