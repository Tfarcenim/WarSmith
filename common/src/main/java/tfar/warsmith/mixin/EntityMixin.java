package tfar.warsmith.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.warsmith.duck.DamageSourcesDuck;
import tfar.warsmith.duck.EntityDuck;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityDuck {


    private static final EntityDataAccessor<Boolean> ON_SOUL_FIRE = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BOOLEAN);

    @Inject(method = "<init>",at = @At("RETURN"))
    private void registerData(EntityType $$0, Level $$1, CallbackInfo ci) {
        this.entityData.define(ON_SOUL_FIRE,false);
    }

    @Shadow public abstract int getTicksFrozen();

    @Shadow public abstract void setTicksFrozen(int pTicksFrozen);

    @Shadow private BlockPos blockPosition;

    @Shadow @Final protected SynchedEntityData entityData;

    @Shadow private Level level;

    @Shadow public abstract boolean isSpectator();

    @Inject(method = "baseTick",at = @At("HEAD"))
    private void entityTickEvent(CallbackInfo ci) {
        Entity self = selfCast();
        if (self.level().isClientSide) {
            this.clearSoulFire();
        } else if (this.remainingSoulFireTicks > 0) {
            if (remainingSoulFireTicks % 20 == 0) {
                self.hurt(((DamageSourcesDuck)self.damageSources()).onSoulFire(), 1.0F);
            }
            this.setRemainingSoulFireTicks(this.remainingSoulFireTicks - 1);
            if (this.getTicksFrozen() > 0) {
                this.setTicksFrozen(0);
                self.level().levelEvent(null, LevelEvent.SOUND_EXTINGUISH_FIRE, this.blockPosition, 1);
            }
        }

        if (!this.level.isClientSide) {
            this.setFlagOnSoulFire(this.remainingSoulFireTicks > 0);
        }

    }

    @Unique
    private int remainingSoulFireTicks;
    @Unique
    private boolean hasVisualSoulFire;

    public void setRemainingSoulFireTicks(int pRemainingFireTicks) {
        this.remainingSoulFireTicks = pRemainingFireTicks;
    }

    public int getRemainingSoulFireTicks() {
        return this.remainingSoulFireTicks;
    }

    /**
     * Removes fire from entity.
     */
    public void clearSoulFire() {
        this.setRemainingSoulFireTicks(0);
    }

    public void setFlagOnSoulFire(boolean pIsOnFire) {
        this.entityData.set(ON_SOUL_FIRE,pIsOnFire || this.hasVisualSoulFire);
    }

    public boolean displaySoulFireAnimation() {
        return this.isOnSoulFire() && !this.isSpectator();
    }

    public boolean isOnSoulFire() {
        boolean flag = this.level != null && this.level.isClientSide;
        return (this.remainingSoulFireTicks > 0 || flag && this.entityData.get(ON_SOUL_FIRE));
    }

    /**
     * Sets entity to burn for x amount of seconds, cannot lower amount of existing fire.
     */
    public void setSecondsOnSoulFire(int pSeconds) {
        int i = pSeconds * 20;
     //   Entity self = selfCast();
      //  if (self instanceof LivingEntity) {
      //      i = ProtectionEnchantment.getFireAfterDampener((LivingEntity) self, i);
      //  }

        if (this.remainingSoulFireTicks < i) {
            this.setRemainingSoulFireTicks(i);
        }
    }

    @Unique
    private Entity selfCast() {
        return (Entity) (Object) this;
    }

}
