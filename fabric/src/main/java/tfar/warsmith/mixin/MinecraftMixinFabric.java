package tfar.warsmith.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.warsmith.client.ClientMisc;

@Mixin(Minecraft.class)
public class MinecraftMixinFabric {

    @Shadow @Final public Options options;

    @Inject(method = "startAttack",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/phys/HitResult;getType()Lnet/minecraft/world/phys/HitResult$Type;"),cancellable = true)
    private void interceptAttack(CallbackInfoReturnable<Boolean> cir) {
        if (ClientMisc.onClickInput(0, this.options.keyAttack, InteractionHand.MAIN_HAND)) {
            cir.setReturnValue(false);
        }
    }
}
