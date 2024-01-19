package tfar.warsmith.data;

import com.google.gson.JsonElement;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import tfar.warsmith.init.ModItems;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelGenerators {

    public ModItemModelProvider(BiConsumer<ResourceLocation, Supplier<JsonElement>> pOutput) {
        super(pOutput);
    }

    @Override
    public void run() {
        this.generateFlatHandheldItems(ModItems.IRON_KATANA,ModItems.DIAMOND_KATANA,ModItems.NETHERITE_KATANA,
                ModItems.IRON_KUSARIGAMA,ModItems.DIAMOND_KUSARIGAMA,ModItems.NETHERITE_KUSARIGAMA,
                ModItems.IRON_SAI,ModItems.DIAMOND_SAI,ModItems.NETHERITE_SAI
        );
    }

    public void generateFlatItems(Item... items) {
        for (Item item : items) {
            generateFlatItem(item,ModelTemplates.FLAT_ITEM);
        }
    }

    public void generateFlatHandheldItems(Item... items) {
        for (Item item : items) {
            generateFlatItem(item,ModelTemplates.FLAT_HANDHELD_ITEM);
        }
    }
}
