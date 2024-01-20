package tfar.warsmith.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import tfar.warsmith.client.render.KusarigamaEntityRenderer;
import tfar.warsmith.duck.PlayerDuck;
import tfar.warsmith.init.ModEntityTypes;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.item.KusarigamaItem;
import tfar.warsmith.platform.ClientHelper;
import tfar.warsmith.platform.Services;
import tfar.warsmith.tags.ModItemTags;

public class ClientMisc {
    public static final Material SOUL_FIRE_0 = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation("block/soul_fire_0"));
    public static final Material SOUL_FIRE_1 = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation("block/soul_fire_1"));

    public static boolean heldItemRender(LivingEntity livingEntity, ItemStack stack) {
        return stack.is(ModItemTags.KATANAS) && livingEntity.isInvisible();
    }

    public static final ResourceLocation CAST_PREDICATE = new ResourceLocation("cast");
    public static void clientSetup() {
        ClientHelper clientHelper = Services.PLATFORM.getClientHelper();
        clientHelper.registerEntityRenderer(ModEntityTypes.KUSARIGAMA_ENTITY, KusarigamaEntityRenderer::new);

        ItemProperties.register(ModItems.IRON_KUSARIGAMA,CAST_PREDICATE,(stack, $$1, living, $$3) -> {
            if (living == null) {
                return 0.0F;
            } else {
                boolean $$4 = living.getMainHandItem() == stack;
                boolean $$5 = living.getOffhandItem() == stack;
                if (living.getMainHandItem().getItem() instanceof KusarigamaItem) {
                    $$5 = false;
                }

                return ($$4 || $$5) && living instanceof Player && ((PlayerDuck)living).getKusarigama() != null ? 1.0F : 0.0F;
            }
        });
    }

}
