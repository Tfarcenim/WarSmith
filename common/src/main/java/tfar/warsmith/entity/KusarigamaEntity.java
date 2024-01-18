package tfar.warsmith.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import tfar.warsmith.duck.PlayerDuck;

import javax.annotation.Nullable;

public class KusarigamaEntity extends Projectile {
    public KusarigamaEntity(EntityType<? extends Projectile> $$0, Level $$1) {
        super($$0, $$1);
    }


    @Nullable
    private Entity hookedIn;

    @Override
    protected void defineSynchedData() {

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
        boolean flag = itemstack.canPerformAction(net.minecraftforge.common.ToolActions.FISHING_ROD_CAST);
        boolean flag1 = itemstack1.canPerformAction(net.minecraftforge.common.ToolActions.FISHING_ROD_CAST);
        if (!pPlayer.isRemoved() && pPlayer.isAlive() && (flag || flag1) && !(this.distanceToSqr(pPlayer) > 1024.0D)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }


}
