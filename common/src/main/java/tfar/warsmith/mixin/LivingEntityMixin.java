package tfar.warsmith.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.warsmith.WarSmith;

@Debug(export = true)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique
    private static final ThreadLocal<Float> baseDamage = ThreadLocal.withInitial(() -> 0f);
    @Unique
    private static final ThreadLocal<DamageSource> damageSource = ThreadLocal.withInitial(() -> null);

    @Inject(method = "getDamageAfterArmorAbsorb",at = @At("HEAD"))
    private void captureBaseDamage(DamageSource $$0, float $$1, CallbackInfoReturnable<Float> cir) {
        damageSource.set($$0);
        baseDamage.set($$1);
    }

    @ModifyArg(method = "getDamageAfterArmorAbsorb",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterAbsorb(FFF)F"),index = 1)
    private float nerfArmor(float original) {
        return original * WarSmith.armorMultiplier(damageSource.get());
    }

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("RETURN"), cancellable = true)
    private void modifyDamage(DamageSource source, float damage, CallbackInfoReturnable<Float> cir) {
        float bonus = WarSmith.adjustDamage((LivingEntity) (Object)this,source,baseDamage.get(),cir.getReturnValue());
        if (bonus > 0) {
            cir.setReturnValue(cir.getReturnValue() + bonus);
        }
    }

    //mainly needed for fabric, should this be moved out of common?
    @Inject(method = "dropCustomDeathLoot",at = @At("HEAD"))
    private void addSkullCrusherHeads(DamageSource pDamageSource, int pLooting, boolean pHitByPlayer, CallbackInfo ci) {
        WarSmith.onDropCustomLoot((LivingEntity) (Object)this,pDamageSource,pLooting,pHitByPlayer);
    }
}
