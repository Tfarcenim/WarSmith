package tfar.warsmith.enchantment.categories;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import tfar.warsmith.platform.Services;
import tfar.warsmith.tags.ModItemTags;

public class ModEnchantmentCategories {

    public static final EnchantmentCategory KATANA = Services.PLATFORM.create("KATANA", ModItemTags.KATANAS);
    public static final EnchantmentCategory KUSARIGAMA = Services.PLATFORM.create("KUSARIGAMA", ModItemTags.KATANAS);

}
