package tfar.warsmith.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.warsmith.WarSmith;
import tfar.warsmith.duck.PlayerDuck;
import tfar.warsmith.entity.KusarigamaEntity;

@Debug(export = true)
@Mixin(Player.class)
public class PlayerMixin implements PlayerDuck {

    private boolean hasOpportunisticStrike;

    @ModifyVariable(method = "attack",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/world/entity/LivingEntity;)I"),ordinal = 0)
    private float bonusSneakAttack(float damage,Entity entity) {
        return damage * WarSmith.getTotalMultipliers(damage,selfCast(),entity);
    }

    @ModifyVariable(method = "attack",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/player/Player;getAttackStrengthScale(F)F"),ordinal = 0)
    private float boostBaseDamage(float damage, Entity entity) {
        return damage * WarSmith.getClaymoreMultiplier(selfCast(),entity);
    }

    @Unique
    private Player selfCast() {
        return (Player) (Object)this;
    }

    @Nullable private KusarigamaEntity kusarigama;

    @Nullable
    @Override
    public KusarigamaEntity getKusarigama() {
        return kusarigama;
    }

    @Override
    public void setKusarigama(KusarigamaEntity kusarigama) {
        this.kusarigama = kusarigama;
    }

    @Override
    public boolean hasOpportunisticStrike() {
        return hasOpportunisticStrike;
    }

    @Override
    public void setOpportunisticStrike(boolean opportunity) {
        hasOpportunisticStrike = opportunity;
    }
}
