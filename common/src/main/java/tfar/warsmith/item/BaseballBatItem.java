package tfar.warsmith.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import tfar.warsmith.WarSmith;
import tfar.warsmith.platform.Services;

public class BaseballBatItem extends WeaponItem {
    private final double knockback;

    public BaseballBatItem(Tier $$0, float attackDamage, double attackSpeed, double knockback, Properties $$1) {
        super($$0, attackDamage, attackSpeed, $$1, Handedness.TWO_HAND);
        this.knockback = knockback;
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack $$0) {
        return 7200;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity living, int pTimeLeft) {
        int ticksCharged = this.getUseDuration(pStack) - pTimeLeft;

        float charge = Math.min(ticksCharged / 40f,1);

        if (living instanceof ServerPlayer player) {
            double reach = Services.PLATFORM.getEntityReach(living);
            Vec3 vec3 = living.getEyePosition(0);
            double d1 = reach * reach;
            Vec3 look = living.getViewVector(1.0F);
            Vec3 vec32 = vec3.add(look.x * reach, look.y * reach, look.z * reach);
            AABB aabb = living.getBoundingBox().expandTowards(look.scale(reach)).inflate(1.0D, 1.0D, 1.0D);
            EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(living, vec3, vec32, aabb, (p_234237_) -> {
                return !p_234237_.isSpectator() && p_234237_.isPickable();
            }, d1);
            if (entityhitresult != null) {
                Entity entity = entityhitresult.getEntity();
                if (entity.isAttackable()) {
                    AttributeModifier swingBoost = new AttributeModifier("Charged Swing",4 * charge, AttributeModifier.Operation.ADDITION);
                    player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(swingBoost);
                    pStack.enchant(Enchantments.KNOCKBACK,(int) (knockback * charge));

                 //   AttributeModifier knockbackBoost = new AttributeModifier("Charged Knockback",knockback * charge, AttributeModifier.Operation.ADDITION);
                 //   player.getAttribute(Attributes.ATTACK_KNOCKBACK).addTransientModifier(knockbackBoost);

                    player.resetAttackStrengthTicker();
                    player.attack(entity);
                    player.swing(InteractionHand.MAIN_HAND);
                    player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(swingBoost);
                    WarSmith.removeEnchantment(pStack, Enchantments.KNOCKBACK);
            //        player.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(knockbackBoost);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack $$0) {
        return UseAnim.SPEAR;
    }
}
