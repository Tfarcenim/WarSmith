package tfar.warsmith.enchantment.categories;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import tfar.warsmith.platform.Services;
import tfar.warsmith.tags.ModItemTags;

public class ModEnchantmentCategories {
    public static final EnchantmentCategory KATANA = Services.PLATFORM.create("KATANA", ModItemTags.KATANAS);
    public static final EnchantmentCategory KUSARIGAMA = Services.PLATFORM.create("KUSARIGAMA", ModItemTags.KUSARIGAMAS);
    public static final EnchantmentCategory SAI = Services.PLATFORM.create("SAI", ModItemTags.SAIS);
    public static final EnchantmentCategory HALBERD = Services.PLATFORM.create("HALBERD", ModItemTags.HALBERDS);
    public static final EnchantmentCategory BATTLEAXE = Services.PLATFORM.create("BATTLEAXE",ModItemTags.BATTLEAXES);
    public static final EnchantmentCategory RAPIER = Services.PLATFORM.create("RAPIER",ModItemTags.RAPIERS);
    public static final EnchantmentCategory SLEIGHT_OF_HAND = Services.PLATFORM.create("SLEIGHT_OF_HAND",ModItemTags.CAN_APPLY_SLEIGHT_OF_HAND);

    //todo remove in 1.20.4+
    public static final EnchantmentCategory BANE_OF_ARTHROPODS = Services.PLATFORM.create("BANE_OF_ARTHROPODS", ModItemTags.CAN_APPLY_BANE_OF_ARTHROPODS);
    public static final EnchantmentCategory FIRE_ASPECT = Services.PLATFORM.create("FIRE_ASPECT", ModItemTags.CAN_APPLY_FIRE_ASPECT);
    public static final EnchantmentCategory KNOCKBACK = Services.PLATFORM.create("KNOCKBACK", ModItemTags.CAN_APPLY_KNOCKBACK);
    public static final EnchantmentCategory LOOTING = Services.PLATFORM.create("LOOTING", ModItemTags.CAN_APPLY_LOOTING);
    public static final EnchantmentCategory SHARPNESS = Services.PLATFORM.create("SHARPNESS", ModItemTags.CAN_APPLY_SHARPNESS);
    public static final EnchantmentCategory SMITE = Services.PLATFORM.create("SMITE", ModItemTags.CAN_APPLY_SMITE);

}
