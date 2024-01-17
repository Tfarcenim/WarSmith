package tfar.warsmith.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.warsmith.client.ClientMisc;

@Mixin(ItemInHandLayer.class)
public class ItemInHandLayerMixin {


    @Inject(method = "renderArmWithItem",at = @At(value = "INVOKE",target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"), cancellable = true)
    private void onItemRender(LivingEntity livingEntity, ItemStack stack, ItemDisplayContext $$2, HumanoidArm $$3, PoseStack $$4, MultiBufferSource $$5, int $$6, CallbackInfo ci) {
        if (ClientMisc.heldItemRender(livingEntity,stack)) {
            ci.cancel();
        }
    }
}
