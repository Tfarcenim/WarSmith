package tfar.warsmith.enchantment.categories;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import tfar.warsmith.platform.Services;
import tfar.warsmith.tags.ModItemTags;

import java.util.function.Predicate;

public class ModEnchantmentCategories {
    public static final EnchantmentCategory KATANA = Services.PLATFORM.create("KATANA", ModItemTags.KATANAS);
    public static final EnchantmentCategory KUSARIGAMA = Services.PLATFORM.create("KUSARIGAMA", ModItemTags.KUSARIGAMAS);
    public static final EnchantmentCategory SAI = Services.PLATFORM.create("SAI", ModItemTags.SAIS);
    public static final EnchantmentCategory HALBERD = Services.PLATFORM.create("HALBERD", ModItemTags.HALBERDS);
    public static final EnchantmentCategory BATTLEAXE = Services.PLATFORM.create("BATTLEAXE",ModItemTags.BATTLEAXES);

    //todo remove in 1.20.4+
    public static final EnchantmentCategory FIRE_ASPECT = Services.PLATFORM.create("FIRE_ASPECT", ModItemTags.CAN_APPLY_FIRE_ASPECT);
    public static final EnchantmentCategory KNOCKBACK = Services.PLATFORM.create("KNOCKBACK", ModItemTags.CAN_APPLY_KNOCKBACK);

    public static Predicate<Item> createFireAspectWrapper() {
        return  item -> item.builtInRegistryHolder().is(ModItemTags.KUSARIGAMAS) || EnchantmentCategory.WEAPON.canEnchant(item);
    }

}
