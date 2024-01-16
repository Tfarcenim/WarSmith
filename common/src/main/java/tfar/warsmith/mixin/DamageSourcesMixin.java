package tfar.warsmith.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.warsmith.duck.DamageSourcesDuck;
import tfar.warsmith.init.Misc;

@Mixin(DamageSources.class)
public abstract class DamageSourcesMixin implements DamageSourcesDuck {

    @Shadow protected abstract DamageSource source(ResourceKey<DamageType> pDamageTypeKey);

    @Unique
    private DamageSource onSoulFire;

    @Inject(method = "<init>",at = @At("RETURN"))
    private void onDamageSourceCreation(RegistryAccess $$0, CallbackInfo ci) {
        onSoulFire = this.source(Misc.ON_SOUL_FIRE);
    }

    @Override
    public DamageSource onSoulFire() {
        return onSoulFire;
    }
}
