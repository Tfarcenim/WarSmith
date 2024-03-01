package tfar.warsmith;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class ModEarlyRiser implements Runnable{

    //todo remove in 1.20.5+

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("KATANA", "tfar.warsmith.enchantment.categories.KatanaEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("KUSARIGAMA", "tfar.warsmith.enchantment.categories.KusarigamaEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("SAI", "tfar.warsmith.enchantment.categories.SaiEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("HALBERD", "tfar.warsmith.enchantment.categories.HalberdEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("BATTLEAXE", "tfar.warsmith.enchantment.categories.BattleAxeEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("RAPIER", "tfar.warsmith.enchantment.categories.RapierEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("MACE", "tfar.warsmith.enchantment.categories.MaceEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("SLEIGHT_OF_HAND", "tfar.warsmith.enchantment.categories.SleightOfHandEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("BASEBALL_BAT", "tfar.warsmith.enchantment.categories.BaseballBatEnchantmentCategory")
                .build();


        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("BANE_OF_ARTHROPODS", "tfar.warsmith.enchantment.categories.BaneOfArthropodsEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("FIRE_ASPECT", "tfar.warsmith.enchantment.categories.FireAspectEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("KNOCKBACK", "tfar.warsmith.enchantment.categories.KnockbackEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("LOOTING", "tfar.warsmith.enchantment.categories.LootingEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("SHARPNESS", "tfar.warsmith.enchantment.categories.SharpnessEnchantmentCategory")
                .build();

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("SMITE", "tfar.warsmith.enchantment.categories.SmiteEnchantmentCategory")
                .build();



    }
}
