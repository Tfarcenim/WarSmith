package tfar.warsmith.client;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import tfar.warsmith.client.perspective.BakedPerspectiveModel;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.platform.FabricClientHelper;
import tfar.warsmith.platform.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientWarSmithFabric implements ClientModInitializer {

    public static final List<Item> PERSPECTIVE_ITEMS = new ArrayList<>();

    static {
        PERSPECTIVE_ITEMS.add(ModItems.IRON_MACE);
        PERSPECTIVE_ITEMS.add(ModItems.DIAMOND_MACE);
        PERSPECTIVE_ITEMS.add(ModItems.NETHERITE_MACE);
        PERSPECTIVE_ITEMS.add(ModItems.BASEBALL_BAT);
        PERSPECTIVE_ITEMS.add(ModItems.SPIKED_BASEBALL_BAT);
        PERSPECTIVE_ITEMS.add(ModItems.NETHERITE_BASEBALL_BAT);
    }

    @Override
    public void onInitializeClient() {
        Services.PLATFORM.setClientHelper(new FabricClientHelper());
        ClientMisc.clientSetup();
        ModelLoadingPlugin.register(pluginContext -> pluginContext.addModels(PERSPECTIVE_ITEMS.stream().map(ClientWarSmithFabric::sprite).toList()));
    }

    private static ResourceLocation sprite(Item path) {
        return BuiltInRegistries.ITEM.getKey(path).withPrefix("item/sprite/");
    }


    public static void onModelBake(Map<ResourceLocation, BakedModel> bakedTopLevelModels, ModelBakery modelBakery) {

      //  Map<ResourceLocation,BakedModel> gauntletModels = new HashMap<>();

        //bakedTopLevelModels.entrySet().stream()
    //            .filter(resourceLocationBakedModelEntry -> resourceLocationBakedModelEntry.getKey().getPath().contains("potioneer_gauntlet"))
     //           .forEach(resourceLocationBakedModelEntry -> gauntletModels.put(resourceLocationBakedModelEntry.getKey(),resourceLocationBakedModelEntry.getValue()));

        for (Item item:PERSPECTIVE_ITEMS) {
            createGuiSpriteModel(item,bakedTopLevelModels);
        }
    }

    private static void createGuiSpriteModel(Item item,Map<ResourceLocation, BakedModel> bakedTopLevelModels) {
        ResourceLocation path = getDefaultModel(item);

        ResourceLocation spriteLocation = sprite(item);

        BakedModel bakedModel = bakedTopLevelModels.get(path);
        if (bakedModel != null) {
            BakedModel spriteModel = bakedTopLevelModels.get(spriteLocation);
            if (spriteModel == null) throw new RuntimeException("Sprite model missing! "+spriteLocation);
            BakedPerspectiveModel bakedPerspectiveModel = new BakedPerspectiveModel(bakedModel,
                    ImmutableMap.<ItemDisplayContext, BakedModel>builder()
                            .put(ItemDisplayContext.GUI,bakedTopLevelModels.get(spriteLocation))
                    .build());
            bakedTopLevelModels.put(path,bakedPerspectiveModel);
        } else {
            throw new RuntimeException("Default model missing! "+path);
        }
    }

    public static ResourceLocation getDefaultModel(Item item) {
        ResourceLocation registryName = BuiltInRegistries.ITEM.getKey(item);
        return new ModelResourceLocation(registryName,"inventory");
    }


}
