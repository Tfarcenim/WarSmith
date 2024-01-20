package tfar.warsmith.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import tfar.warsmith.duck.PlayerDuck;
import tfar.warsmith.init.ModEntityTypes;
import tfar.warsmith.item.KusarigamaItem;
import tfar.warsmith.platform.Services;

import javax.annotation.Nullable;

public class KusarigamaEntity extends Projectile {
    private int thievingChain;

    public KusarigamaEntity(EntityType<? extends KusarigamaEntity> $$0, Level $$1) {
        super($$0, $$1);
    }
    private int life;
    private ChainState currentState = ChainState.FLYING;

    public KusarigamaEntity(Player player, Level $$1, int thievingChain) {
        this(ModEntityTypes.KUSARIGAMA_ENTITY, $$1);
        this.thievingChain = thievingChain;
        this.setOwner(player);
        float xRot = player.getXRot();
        float yRot = player.getYRot();
        float $$6 = Mth.cos(-yRot * (float) (Math.PI / 180.0) - (float) Math.PI);
        float $$7 = Mth.sin(-yRot * (float) (Math.PI / 180.0) - (float) Math.PI);
        float $$8 = -Mth.cos(-xRot * (float) (Math.PI / 180.0));
        float $$9 = Mth.sin(-xRot * (float) (Math.PI / 180.0));
        double xpos = player.getX() - $$7 * 0.3;
        double ypos = player.getEyeY();
        double zpos = player.getZ() - $$6 * 0.3;
        this.moveTo(xpos, ypos, zpos, yRot, xRot);
        Vec3 $$13 = new Vec3(-$$7, Mth.clamp(-($$9 / $$8), -5.0F, 5.0F), -$$6);
        double $$14 = $$13.length();
        $$13 = $$13.multiply(
                0.6 / $$14 + this.random.triangle(0.5, 0.0103365),
                0.6 / $$14 + this.random.triangle(0.5, 0.0103365),
                0.6 / $$14 + this.random.triangle(0.5, 0.0103365)
        );
        this.setDeltaMovement($$13);
        this.setYRot((float)(Mth.atan2($$13.x, $$13.z) * 180.0F / (float)Math.PI));
        this.setXRot((float)(Mth.atan2($$13.y, $$13.horizontalDistance()) * 180.0F / (float)Math.PI));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
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

    public void onClientRemoval() {
        this.updateOwnerInfo(null);
    }

    public void setOwner(@Nullable Entity pOwner) {
        super.setOwner(pOwner);
        this.updateOwnerInfo(this);
    }

    private void updateOwnerInfo(@Nullable KusarigamaEntity kusarigamaEntity) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            ((PlayerDuck)player).setKusarigama(kusarigamaEntity);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        Player player = this.getPlayerOwner();
        if (player == null) {
            this.discard();
        } else if (this.level().isClientSide || !this.shouldRemoveChain(player)) {
            if (this.onGround()) {
                ++this.life;
                if (this.life >= 1200) {
                    this.discard();
                    return;
                }
            } else {
                this.life = 0;
            }

            float f = 0.0F;
            BlockPos blockpos = this.blockPosition();
            FluidState fluidstate = this.level().getFluidState(blockpos);
            if (fluidstate.is(FluidTags.WATER)) {
                f = fluidstate.getHeight(this.level(), blockpos);
            }

            boolean flag = f > 0.0F;
            if (this.currentState == ChainState.FLYING) {
                if (this.hookedIn != null) {
                    this.setDeltaMovement(Vec3.ZERO);
                    this.currentState = ChainState.HOOKED_IN_ENTITY;
                    return;
                }

                if (flag) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 0.4D, 0.6D));
                    return;
                }

                this.checkCollision();
            } else {
                if (this.currentState == ChainState.HOOKED_IN_ENTITY) {
                    if (this.hookedIn != null) {
                        if (!this.hookedIn.isRemoved() && this.hookedIn.level().dimension() == this.level().dimension()) {
                            this.setPos(this.hookedIn.getX(), this.hookedIn.getY(0.8D), this.hookedIn.getZ());
                        } else {
                            this.setHookedEntity(null);
                            this.currentState = ChainState.FLYING;
                        }
                    }

                    return;
                }

            }

            if (!fluidstate.is(FluidTags.WATER)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            this.updateRotation();
            if (this.currentState == ChainState.FLYING && (this.onGround() || this.horizontalCollision)) {
                this.setDeltaMovement(Vec3.ZERO);
            }

            double d1 = 0.92D;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.92D));
            this.reapplyPosition();
        }
    }

    private void checkCollision() {
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
       if (hitresult.getType() == HitResult.Type.MISS || !Services.PLATFORM.fireProjectileImpactEvent(this, hitresult)) this.onHit(hitresult);
    }

    enum ChainState {
        FLYING,
        HOOKED_IN_ENTITY
    }

    public void remove(Entity.RemovalReason pReason) {
        this.updateOwnerInfo(null);
        super.remove(pReason);
    }

    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }


    public int retrieve(ItemStack stack) {
        Player player = this.getPlayerOwner();
        if (!this.level().isClientSide && player != null && !this.shouldRemoveChain(player)) {
            if (this.hookedIn != null) {
                this.pullEntity(this.hookedIn);
                disarm(this.hookedIn);
                this.level().broadcastEntityEvent(this, (byte)31);
            }

            this.discard();
            return 1;
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

    protected void disarm(Entity entity) {
        if (entity instanceof LivingEntity living) {
            InteractionHand hand = random.nextBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
            ItemStack held = living.getItemInHand(hand);
            if (!held.isEmpty()) {
                boolean theivingChainActive = .25 * thievingChain > random.nextDouble();
                Player player = getPlayerOwner();
                if (theivingChainActive && player != null) {
                    if (!player.getInventory().add(held)) {
                        ItemEntity itemEntity = new ItemEntity(level(), player.getX(), player.getY(), player.getZ(), held);
                        level().addFreshEntity(itemEntity);
                    }
                } else {
                    ItemEntity itemEntity = new ItemEntity(level(), entity.getX(), entity.getY(), entity.getZ(), held);
                    Vec3 vec3 = new Vec3(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ()).scale(0.1D);
                    itemEntity.setDeltaMovement(vec3);
                    level().addFreshEntity(itemEntity);
                }
                living.setItemInHand(hand,ItemStack.EMPTY);
            }
        }
    }

    private boolean shouldRemoveChain(Player pPlayer) {
        ItemStack itemstack = pPlayer.getMainHandItem();
        ItemStack itemstack1 = pPlayer.getOffhandItem();
        boolean flag = itemstack.getItem() instanceof KusarigamaItem;
        boolean flag1 = itemstack1.getItem() instanceof KusarigamaItem;
        if (!pPlayer.isRemoved() && pPlayer.isAlive() && (flag || flag1) && !(this.distanceToSqr(pPlayer) > 256)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }


}
