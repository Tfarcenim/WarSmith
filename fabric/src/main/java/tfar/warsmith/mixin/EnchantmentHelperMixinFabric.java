package tfar.warsmith.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixinFabric {

    @Inject(method = {"doPostDamageEffects","doPostHurtEffects"},
            at = @At(value = "INVOKE",target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;runIterationOnItem(Lnet/minecraft/world/item/enchantment/EnchantmentHelper$EnchantmentVisitor;Lnet/minecraft/world/item/ItemStack;)V"),
            cancellable = true)
    private static void MC$248272fix(LivingEntity attacker, Entity target, CallbackInfo ci) {//copied from forge
        ci.cancel();
    }
}
