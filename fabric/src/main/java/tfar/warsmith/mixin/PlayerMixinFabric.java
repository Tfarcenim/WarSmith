package tfar.warsmith.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.warsmith.WarSmithFabric;

@Mixin(Player.class)
public class PlayerMixinFabric {

    @ModifyVariable(method = "attack",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/world/entity/LivingEntity;)I"),print = false,ordinal = 3)
    private boolean hookSweepAttack(boolean original, @Local(ordinal = 0) Entity target,
                                    @Local(ordinal = 0) float f, @Local(ordinal = 1)  float g, @Local(ordinal = 2)  float h,
                                    @Local(ordinal = 0) boolean fullAttackStrength,  @Local(ordinal = 1) boolean bl2,  @Local(ordinal = 0) int i,
                                    @Local(ordinal = 2)  boolean bl3) {
        return WarSmithFabric.forceSweep((Player) (Object) this,original,fullAttackStrength,bl2,bl3);
    }

   // @Inject(method = "attack",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/world/entity/LivingEntity;)I"),locals = LocalCapture.PRINT)
   // private void hookSweepAttack1(Entity target, CallbackInfo ci) {
   // }
}
