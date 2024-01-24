package tfar.warsmith.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import tfar.warsmith.WarSmith;
import tfar.warsmith.client.ClientMisc;
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
        generateKusarigama(ModItems.DIAMOND_KUSARIGAMA);
        generateKusarigama(ModItems.NETHERITE_KUSARIGAMA);

        generateFlatItem(ModItems.CLAYMORE,LARGE_FLAT_HANDHELD_ITEM);
    }

    public void generateKusarigama(KusarigamaItem kusarigamaItem) {
        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(kusarigamaItem);
        ResourceLocation resourcelocation1 = TextureMapping.getItemTexture(kusarigamaItem);
        TWO_LAYERED_HANDHELD_ITEM.create(resourcelocation, TextureMapping.layered(resourcelocation1,
                        new ResourceLocation(WarSmith.MOD_ID,"item/kusarigama_chain")), this.output,
                this::generateBaseKusarigama);

        ModelTemplates.FLAT_HANDHELD_ITEM.create(ModelLocationUtils.getModelLocation(kusarigamaItem).withSuffix("_cast"),
                TextureMapping.layer0(kusarigamaItem), this.output);
    }

    public JsonObject generateBaseKusarigama(ResourceLocation pModelLocation, Map<TextureSlot, ResourceLocation> template) {
        JsonObject jsonobject = TWO_LAYERED_HANDHELD_ITEM.createBaseTemplate(pModelLocation, template);
        JsonArray jsonarray = new JsonArray();

            JsonObject overrideJson = new JsonObject();
            JsonObject predicateJson = new JsonObject();
            predicateJson.addProperty(ClientMisc.CAST_PREDICATE.toString(), 1);
            overrideJson.add("predicate", predicateJson);
            overrideJson.addProperty("model",pModelLocation.withSuffix("_cast").toString());
            jsonarray.add(overrideJson);

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

    public static final ModelTemplate LARGE_FLAT_HANDHELD_ITEM = createItem(WarSmith.MOD_ID,"large_handheld", TextureSlot.LAYER0);

    public static final ModelTemplate TWO_LAYERED_HANDHELD_ITEM = createItem("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

    private static ModelTemplate createItem(String pItemModelLocation, TextureSlot... pRequiredSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation("minecraft", "item/" + pItemModelLocation)), Optional.empty(), pRequiredSlots);
    }

    private static ModelTemplate createItem(String domain, String pItemModelLocation, TextureSlot... pRequiredSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation(domain, "item/" + pItemModelLocation)), Optional.empty(), pRequiredSlots);
    }


}
