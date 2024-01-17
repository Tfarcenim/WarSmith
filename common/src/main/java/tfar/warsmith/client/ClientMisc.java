package tfar.warsmith.client;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import tfar.warsmith.tags.ModItemTags;

public class ClientMisc {
    public static final Material SOUL_FIRE_0 = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation("block/soul_fire_0"));
    public static final Material SOUL_FIRE_1 = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation("block/soul_fire_1"));

    public static boolean heldItemRender(LivingEntity livingEntity, ItemStack stack) {
        return stack.is(ModItemTags.KATANAS) && livingEntity.isInvisible();
    }

}
