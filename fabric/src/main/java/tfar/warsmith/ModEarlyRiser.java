package tfar.warsmith;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import tfar.warsmith.tags.ModItemTags;

public class ModEarlyRiser implements Runnable{
    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");

        //String tagkeyParam = "L"+remapper.mapClassName("intermediary","net.minecraft.class_6862")+";";

        ClassTinkerers.enumBuilder(enchantmentTarget)
                .addEnumSubclass("KATANA", "tfar.warsmith.enchantment.categories.KatanaEnchantmentCategory")
                .build();
    }
}
