package tfar.warsmith.platform;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface ClientHelper {

    <E extends Entity>  void registerEntityRenderer(EntityType<? extends E>entityType, EntityRendererProvider<E> entityRendererFactory);

}
