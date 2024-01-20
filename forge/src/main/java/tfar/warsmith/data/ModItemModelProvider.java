package tfar.warsmith.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.item.KusarigamaItem;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelGenerators {

    public ModItemModelProvider(BiConsumer<ResourceLocation, Supplier<JsonElement>> pOutput) {
        super(pOutput);
    }

    @Override
    public void run() {
        this.generateFlatHandheldItems(ModItems.IRON_KATANA,ModItems.DIAMOND_KATANA,ModItems.NETHERITE_KATANA,
            //    ModItems.IRON_KUSARIGAMA,ModItems.DIAMOND_KUSARIGAMA,ModItems.NETHERITE_KUSARIGAMA,
                ModItems.IRON_SAI,ModItems.DIAMOND_SAI,ModItems.NETHERITE_SAI
        );
        generateKusarigama(ModItems.IRON_KUSARIGAMA);

    }

    public void generateKusarigama(KusarigamaItem kusarigamaItem) {
        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(kusarigamaItem);
        ResourceLocation resourcelocation1 = TextureMapping.getItemTexture(kusarigamaItem);
        ModelTemplates.FLAT_ITEM.create(resourcelocation, TextureMapping.layer0(resourcelocation1), this.output, (p_267905_, p_267906_) -> {
            return this.generateBaseKusarigama(p_267905_, p_267906_, kusarigamaItem.getTier());
        });
    }

    public JsonObject generateBaseKusarigama(ResourceLocation pModelLocation, Map<TextureSlot, ResourceLocation> p_267324_, Tier pArmorMaterial) {
        JsonObject jsonobject = ModelTemplates.TWO_LAYERED_ITEM.createBaseTemplate(pModelLocation, p_267324_);
        JsonArray jsonarray = new JsonArray();

            JsonObject jsonobject1 = new JsonObject();
            JsonObject jsonobject2 = new JsonObject();
            jsonobject2.addProperty("test_stuff", 1);
            jsonobject1.add("predicate", jsonobject2);
            jsonobject1.addProperty("model","test:model");
            jsonarray.add(jsonobject1);

        jsonobject.add("overrides", jsonarray);
        return jsonobject;
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

    public static final ModelTemplate TWO_LAYERED_HANDHELD_ITEM = createItem("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

    private static ModelTemplate createItem(String pItemModelLocation, TextureSlot... pRequiredSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation("minecraft", "item/" + pItemModelLocation)), Optional.empty(), pRequiredSlots);
    }

}
