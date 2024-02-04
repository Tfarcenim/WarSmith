package tfar.warsmith.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.warsmith.init.ModEnchantments;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getSweepingDamageRatio",at = @At("HEAD"),cancellable = true)
    private static void checkDeadly(LivingEntity $$0, CallbackInfoReturnable<Float> cir) {
        boolean deadly  = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DEADLY_SWEEP, $$0) > 0;
        if (deadly) cir.setReturnValue(1f);
    }
}
