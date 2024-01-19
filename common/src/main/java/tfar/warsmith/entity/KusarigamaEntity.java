package tfar.warsmith.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import tfar.warsmith.duck.PlayerDuck;
import tfar.warsmith.item.KusarigamaItem;

import javax.annotation.Nullable;

public class KusarigamaEntity extends Projectile {
    public KusarigamaEntity(EntityType<? extends Projectile> $$0, Level $$1) {
        super($$0, $$1);
    }
    private static final EntityDataAccessor<Integer> DATA_HOOKED_ENTITY = SynchedEntityData.defineId(KusarigamaEntity.class, EntityDataSerializers.INT);


    @Nullable
    private Entity hookedIn;

    @Override
    protected void defineSynchedData() {
        entityData.define(DATA_HOOKED_ENTITY,0);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_HOOKED_ENTITY.equals(pKey)) {
            int i = this.getEntityData().get(DATA_HOOKED_ENTITY);
            this.hookedIn = i > 0 ? this.level().getEntity(i - 1) : null;
        }
        super.onSyncedDataUpdated(pKey);
    }

    protected boolean canHitEntity(Entity p_37135_) {
        return super.canHitEntity(p_37135_) || p_37135_.isAlive() && p_37135_ instanceof ItemEntity;
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            this.setHookedEntity(pResult.getEntity());
        }
    }

    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.setDeltaMovement(this.getDeltaMovement().normalize().scale(pResult.distanceTo(this)));
    }

    private void setHookedEntity(@Nullable Entity pHookedEntity) {
        this.hookedIn = pHookedEntity;
        this.getEntityData().set(DATA_HOOKED_ENTITY, pHookedEntity == null ? 0 : pHookedEntity.getId() + 1);
    }

    public void setOwner(@Nullable Entity pOwner) {
        super.setOwner(pOwner);
        this.updateOwnerInfo(this);
    }

    private void updateOwnerInfo(@Nullable KusarigamaEntity pFishingHook) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            ((PlayerDuck)player).setKusarigama(pFishingHook);
        }

    }

    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }


    public int retrieve(ItemStack stack) {
        Player player = this.getPlayerOwner();
        if (!this.level().isClientSide && player != null && !this.shouldRemoveChain(player)) {
            int $$2 = 0;
            if (this.hookedIn != null) {
                this.pullEntity(this.hookedIn);
                this.level().broadcastEntityEvent(this, (byte)31);
                $$2 = this.hookedIn instanceof ItemEntity ? 3 : 5;
            }

            if (this.onGround()) {
                $$2 = 2;
            }

            this.discard();
            return $$2;
        } else {
            return 0;
        }
    }

    protected void pullEntity(Entity pEntity) {
        Entity entity = this.getOwner();
        if (entity != null) {
            Vec3 vec3 = (new Vec3(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ())).scale(0.1D);
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().add(vec3));
        }
    }

    private boolean shouldRemoveChain(Player pPlayer) {
        ItemStack itemstack = pPlayer.getMainHandItem();
        ItemStack itemstack1 = pPlayer.getOffhandItem();
        boolean flag = itemstack.getItem() instanceof KusarigamaItem;
        boolean flag1 = itemstack1.getItem() instanceof KusarigamaItem;
        if (!pPlayer.isRemoved() && pPlayer.isAlive() && (flag || flag1) && !(this.distanceToSqr(pPlayer) > 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }


}
