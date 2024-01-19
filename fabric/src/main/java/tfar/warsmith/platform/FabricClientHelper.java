package tfar.warsmith.platform;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class FabricClientHelper implements ClientHelper {
    @Override
    public <E extends Entity> void registerEntityRenderer(EntityType<? extends E> entityType, EntityRendererProvider<E> entityRendererFactory) {
        EntityRendererRegistry.register(entityType,entityRendererFactory);
    }
}
