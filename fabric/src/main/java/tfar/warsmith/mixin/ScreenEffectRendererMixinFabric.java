package tfar.warsmith.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.warsmith.client.ClientMisc;
import tfar.warsmith.duck.EntityDuck;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixinFabric {

    @Inject(method = "renderScreenEffect",at = @At("RETURN"))
    private static void onRenderScreenEffect(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
        if (!minecraft.player.isSpectator()) {
            if (((EntityDuck)minecraft.player).isOnSoulFire()) {
                renderSoulFire(minecraft,poseStack);
            }
        }
    }

    private static void renderSoulFire(Minecraft $$0, PoseStack $$1) {
        BufferBuilder $$2 = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        TextureAtlasSprite $$3 = ClientMisc.SOUL_FIRE_1.sprite();
        RenderSystem.setShaderTexture(0, $$3.atlasLocation());
        float $$4 = $$3.getU0();
        float $$5 = $$3.getU1();
        float $$6 = ($$4 + $$5) / 2.0F;
        float $$7 = $$3.getV0();
        float $$8 = $$3.getV1();
        float $$9 = ($$7 + $$8) / 2.0F;
        float $$10 = $$3.uvShrinkRatio();
        float $$11 = Mth.lerp($$10, $$4, $$6);
        float $$12 = Mth.lerp($$10, $$5, $$6);
        float $$13 = Mth.lerp($$10, $$7, $$9);
        float $$14 = Mth.lerp($$10, $$8, $$9);
        float $$15 = 1.0F;

        for(int $$16 = 0; $$16 < 2; ++$$16) {
            $$1.pushPose();
            float $$17 = -0.5F;
            float $$18 = 0.5F;
            float $$19 = -0.5F;
            float $$20 = 0.5F;
            float $$21 = -0.5F;
            $$1.translate((float)(-($$16 * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            $$1.mulPose(Axis.YP.rotationDegrees((float)($$16 * 2 - 1) * 10.0F));
            Matrix4f $$22 = $$1.last().pose();
            $$2.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
            $$2.vertex($$22, -0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv($$12, $$14).endVertex();
            $$2.vertex($$22, 0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv($$11, $$14).endVertex();
            $$2.vertex($$22, 0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv($$11, $$13).endVertex();
            $$2.vertex($$22, -0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv($$12, $$13).endVertex();
            BufferUploader.drawWithShader($$2.end());
            $$1.popPose();
        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
    }

}
