package tfar.warsmith.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import tfar.warsmith.WarSmith;
import tfar.warsmith.utils.NoItemDamageExplosion;

import java.util.List;
import java.util.Set;

@Mixin(Explosion.class)
public class ExplosionMixin {

    //probably not needed on forge

    @Inject(method = "explode",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/phys/Vec3;<init>(DDD)V",ordinal = 1),locals = LocalCapture.CAPTURE_FAILHARD)
    private void removeItemsFromExplosion(CallbackInfo ci,Set $$0,int i, float $$18, int $$19, int $$20, int $$21, int $$22, int $$23, int $$24, List<Entity> entities) {
        if ((Object)this instanceof NoItemDamageExplosion) {
            WarSmith.removeItemEntities(entities);
        }
    }
}
