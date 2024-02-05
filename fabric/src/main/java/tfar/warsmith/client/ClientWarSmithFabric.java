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

import java.util.Map;

public class ClientWarSmithFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Services.PLATFORM.setClientHelper(new FabricClientHelper());
        ClientMisc.clientSetup();


        ModelLoadingPlugin.register(pluginContext ->
                pluginContext.addModels(sprite(ModItems.IRON_MACE),sprite(ModItems.DIAMOND_MACE),sprite(ModItems.NETHERITE_MACE)));
    }

    private static ResourceLocation sprite(Item path) {
        return BuiltInRegistries.ITEM.getKey(path).withPrefix("item/sprite/");
    }


    public static void onModelBake(Map<ResourceLocation, BakedModel> bakedTopLevelModels, ModelBakery modelBakery) {

      //  Map<ResourceLocation,BakedModel> gauntletModels = new HashMap<>();

        //bakedTopLevelModels.entrySet().stream()
    //            .filter(resourceLocationBakedModelEntry -> resourceLocationBakedModelEntry.getKey().getPath().contains("potioneer_gauntlet"))
     //           .forEach(resourceLocationBakedModelEntry -> gauntletModels.put(resourceLocationBakedModelEntry.getKey(),resourceLocationBakedModelEntry.getValue()));

        createGuiSpriteModel(ModItems.IRON_MACE,bakedTopLevelModels);
        createGuiSpriteModel(ModItems.DIAMOND_MACE,bakedTopLevelModels);
        createGuiSpriteModel(ModItems.NETHERITE_MACE,bakedTopLevelModels);
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
