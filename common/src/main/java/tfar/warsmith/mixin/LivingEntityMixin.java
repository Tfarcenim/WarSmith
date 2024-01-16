package tfar.warsmith.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.warsmith.WarSmith;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique
    private ThreadLocal<Float> baseDamage = ThreadLocal.withInitial(() -> 0f);

    @Inject(method = "getDamageAfterArmorAbsorb",at = @At("HEAD"))
    private void captureBaseDamage(DamageSource $$0, float $$1, CallbackInfoReturnable<Float> cir) {
        baseDamage.set($$1);
    }

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("RETURN"), cancellable = true)
    private void modifyDamage(DamageSource source, float damage, CallbackInfoReturnable<Float> cir) {
        float bonus = WarSmith.adjustDamage((LivingEntity) (Object)this,source,baseDamage.get(),cir.getReturnValue());
        if (bonus > 0) {
            cir.setReturnValue(cir.getReturnValue() + bonus);
        }
    }
}
