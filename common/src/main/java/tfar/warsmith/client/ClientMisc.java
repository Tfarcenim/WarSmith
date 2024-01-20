package tfar.warsmith.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;
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

    public static final ResourceLocation BLOCKING_PREDICATE = new ResourceLocation("blocking");

    public static final ClampedItemPropertyFunction KUSARIGAMA = (stack, $$1, living, $$3) -> {
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
    };

    public static final ClampedItemPropertyFunction SAI = (stack, $$1, living, $$3) ->
            living != null && living.isUsingItem() && living.getUseItem() == stack ? 1 : 0;


    public static void clientSetup() {
        ClientHelper clientHelper = Services.PLATFORM.getClientHelper();
        clientHelper.registerEntityRenderer(ModEntityTypes.KUSARIGAMA_ENTITY, KusarigamaEntityRenderer::new);

        ItemProperties.register(ModItems.IRON_KUSARIGAMA,CAST_PREDICATE,KUSARIGAMA);
        ItemProperties.register(ModItems.DIAMOND_KUSARIGAMA,CAST_PREDICATE,KUSARIGAMA);
        ItemProperties.register(ModItems.NETHERITE_KUSARIGAMA,CAST_PREDICATE,KUSARIGAMA);

        ItemProperties.register(ModItems.IRON_SAI,BLOCKING_PREDICATE,SAI);
        ItemProperties.register(ModItems.DIAMOND_SAI,BLOCKING_PREDICATE,SAI);
        ItemProperties.register(ModItems.NETHERITE_SAI,BLOCKING_PREDICATE,SAI);
    }

    public static boolean onClickInput(int button, KeyMapping keyBinding, InteractionHand hand) {
        Player player = getClientPlayer();
        return hand == InteractionHand.MAIN_HAND && button == GLFW.GLFW_MOUSE_BUTTON_1 && keyBinding == Minecraft.getInstance().options.keyAttack &&
                player.getCooldowns().isOnCooldown(player.getMainHandItem().getItem());
    }

    public static Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
