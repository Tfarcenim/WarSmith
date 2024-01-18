package tfar.warsmith.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import tfar.warsmith.entity.KusarigamaEntity;

public class ModEntityTypes {

    public static final EntityType<KusarigamaEntity> KUSARIGAMA_ENTITY = EntityType.Builder.of(KusarigamaEntity::new, MobCategory.MISC)
            .noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5)
            .build("kusarigama_entity");

}
