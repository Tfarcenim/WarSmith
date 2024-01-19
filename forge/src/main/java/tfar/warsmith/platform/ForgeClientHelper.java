package tfar.warsmith.platform;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class ForgeClientHelper implements ClientHelper {

    @Override
    public <E extends Entity> void registerEntityRenderer(EntityType<? extends E> entityType, EntityRendererProvider<E> entityRendererFactory) {
        EntityRenderers.register(entityType,entityRendererFactory);
    }
}
