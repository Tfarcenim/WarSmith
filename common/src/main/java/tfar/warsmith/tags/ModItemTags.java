package tfar.warsmith.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import tfar.warsmith.WarSmith;

public class ModItemTags {
    public static final TagKey<Item> KATANAS = bind("katanas");
    public static final TagKey<Item> KUSARIGAMAS = bind("kusarigamas");

    private static TagKey<Item> bind(String $$0) {
        return TagKey.create(Registries.ITEM,new ResourceLocation(WarSmith.MOD_ID,$$0));
    }
}
