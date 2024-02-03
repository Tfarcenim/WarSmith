package tfar.warsmith.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.warsmith.WarSmith;

@Mixin(LivingEntity.class)
public class LivingEntityMixinFabric {

    @Inject(method = "hurt",at = @At("HEAD"),cancellable = true)
    private void fireLivingAttackEvent(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (WarSmith.livingAttackEvent((LivingEntity)(Object) this,source,amount)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "actuallyHurt",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;getCombatTracker()Lnet/minecraft/world/damagesource/CombatTracker;"))
    private void livingDamageEventReplacement(DamageSource damageSource, float damageAmount, CallbackInfo ci) {
        WarSmith.livingDamageEvent((LivingEntity) (Object)this,damageSource,damageAmount);
    }

}
