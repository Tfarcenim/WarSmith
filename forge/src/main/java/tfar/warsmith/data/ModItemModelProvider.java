package tfar.warsmith.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
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
                ModItems.IRON_BATTLEAXE,ModItems.DIAMOND_BATTLEAXE,ModItems.NETHERITE_BATTLEAXE,
                ModItems.RAPIER
                );

        generateSai(ModItems.IRON_SAI);
        generateSai(ModItems.DIAMOND_SAI);
        generateSai(ModItems.NETHERITE_SAI);

        generateKusarigama(ModItems.IRON_KUSARIGAMA);
        generateKusarigama(ModItems.DIAMOND_KUSARIGAMA);
        generateKusarigama(ModItems.NETHERITE_KUSARIGAMA);

        generateFlatItem(ModItems.CLAYMORE,LARGE_FLAT_HANDHELD_ITEM);

        generateFlatItem(ModItems.IRON_HALBERD,EXTRA_LARGE_FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.DIAMOND_HALBERD,EXTRA_LARGE_FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.NETHERITE_HALBERD,EXTRA_LARGE_FLAT_HANDHELD_ITEM);

        makePerspectiveModel(ModItems.IRON_MACE,MACE);
        makePerspectiveModel(ModItems.DIAMOND_MACE, MACE);
        makePerspectiveModel(ModItems.NETHERITE_MACE, MACE);

        makePerspectiveModel(ModItems.BASEBALL_BAT,BASEBALL_BAT);
        makePerspectiveModel(ModItems.SPIKED_BASEBALL_BAT, BASEBALL_BAT);
        makePerspectiveModel(ModItems.NETHERITE_BASEBALL_BAT, BASEBALL_BAT);

        generateCutlass(ModItems.IRON_CUTLASS);
        generateCutlass(ModItems.DIAMOND_CUTLASS);
        generateCutlass(ModItems.NETHERITE_CUTLASS);
    }

    public void makePerspectiveModel(Item item, ModelTemplate template) {
        generateRegularModel(item,template);
        generateSpriteModel(item,ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    public void generateRegularModel(Item pItem, ModelTemplate pModelTemplate) {
        pModelTemplate.create(ModelLocationUtils.getModelLocation(pItem), d3(pItem), this.output);
    }

    public void generateSpriteModel(Item pItem, ModelTemplate pModelTemplate) {
        pModelTemplate.create(getItemSpriteTexture(pItem), sprite(pItem), this.output);
    }

    public static TextureMapping sprite(Item pLayerZeroItem) {
        return new TextureMapping().put(TextureSlot.LAYER0, getItemSpriteTexture(pLayerZeroItem));
    }

    public static TextureMapping d3(Item pLayerZeroItem) {
        return new TextureMapping().put(TextureSlot.LAYER0, getItem3dTexture(pLayerZeroItem));
    }

    public static ResourceLocation getItem3dTexture(Item pItem) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(pItem);
        return resourcelocation.withPrefix("item/3d/");
    }

    public static ResourceLocation getItemSpriteTexture(Item pItem) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(pItem);
        return resourcelocation.withPrefix("item/sprite/");
    }

    public void generateSai(Item item) {
        ResourceLocation base = ModelLocationUtils.getModelLocation(item);
        ResourceLocation texture = TextureMapping.getItemTexture(item);
        ModelTemplates.FLAT_HANDHELD_ITEM.create(base, TextureMapping.layer0(texture), this.output,
                this::generateBaseSai);

        BLOCKING_HANDHELD_ITEM.create(base.withSuffix("_blocking"),
                TextureMapping.layer0(texture), this.output);
    }

    public JsonObject generateBaseSai(ResourceLocation pModelLocation, Map<TextureSlot, ResourceLocation> template) {
        JsonObject jsonobject = ModelTemplates.FLAT_HANDHELD_ITEM.createBaseTemplate(pModelLocation, template);
        JsonArray jsonarray = new JsonArray();

        JsonObject overrideJson = new JsonObject();
        JsonObject predicateJson = new JsonObject();
        predicateJson.addProperty(ClientMisc.BLOCKING_PREDICATE.toString(), 1);
        overrideJson.add("predicate", predicateJson);
        overrideJson.addProperty("model",pModelLocation.withSuffix("_blocking").toString());
        jsonarray.add(overrideJson);

        jsonobject.add("overrides", jsonarray);
        return jsonobject;
    }

    public void generateCutlass(Item item) {
        ResourceLocation base = ModelLocationUtils.getModelLocation(item);
        ResourceLocation texture = TextureMapping.getItemTexture(item);
        ModelTemplates.FLAT_HANDHELD_ITEM.create(base, TextureMapping.layer0(texture), this.output,
                this::generateBaseCutlass);

        BLOCKING_HANDHELD_ITEM.create(base.withSuffix("_blocking"),
                TextureMapping.layer0(texture), this.output);
    }

    public JsonObject generateBaseCutlass(ResourceLocation pModelLocation, Map<TextureSlot, ResourceLocation> template) {
        JsonObject jsonobject = ModelTemplates.FLAT_HANDHELD_ITEM.createBaseTemplate(pModelLocation, template);
        JsonArray jsonarray = new JsonArray();

        JsonObject overrideJson = new JsonObject();
        JsonObject predicateJson = new JsonObject();
        predicateJson.addProperty(ClientMisc.BLOCKING_PREDICATE.toString(), 1);
        overrideJson.add("predicate", predicateJson);
        overrideJson.addProperty("model",pModelLocation.withSuffix("_blocking").toString());
        jsonarray.add(overrideJson);

        jsonobject.add("overrides", jsonarray);
        return jsonobject;
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

    public static final ModelTemplate MACE = createItem(WarSmith.MOD_ID,"mace", TextureSlot.LAYER0);
    public static final ModelTemplate BASEBALL_BAT = createItem(WarSmith.MOD_ID,"baseball_bat_3d", TextureSlot.LAYER0);

    public static final ModelTemplate BLOCKING_HANDHELD_ITEM = createItem(WarSmith.MOD_ID,"cutlass_blocking", TextureSlot.LAYER0);

    public static final ModelTemplate LARGE_FLAT_HANDHELD_ITEM = createItem(WarSmith.MOD_ID,"large_handheld", TextureSlot.LAYER0);
    public static final ModelTemplate EXTRA_LARGE_FLAT_HANDHELD_ITEM = createItem(WarSmith.MOD_ID,"extra_large_handheld", TextureSlot.LAYER0);


    public static final ModelTemplate TWO_LAYERED_HANDHELD_ITEM = createItem("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

    private static ModelTemplate createItem(String pItemModelLocation, TextureSlot... pRequiredSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation("minecraft", "item/" + pItemModelLocation)), Optional.empty(), pRequiredSlots);
    }

    private static ModelTemplate createItem(String domain, String pItemModelLocation, TextureSlot... pRequiredSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation(domain, "item/" + pItemModelLocation)), Optional.empty(), pRequiredSlots);
    }


}
