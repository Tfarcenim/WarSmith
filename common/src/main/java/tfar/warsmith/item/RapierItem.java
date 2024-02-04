package tfar.warsmith.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import tfar.warsmith.init.ModEnchantments;

public class RapierItem extends WeaponItem {
    public RapierItem(Tier $$0, float attackDamage, double attackSpeed, Properties $$1) {
        super($$0, attackDamage, attackSpeed, $$1.durability(0).defaultDurability(1200), Handedness.ONE_HAND);
    }

    public static double dash_speed = .6;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean sneak = player.isCrouching();

        double dashSpeed = dash_speed;
        dashSpeed *= 1 + EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DASHING, stack) / 2d;
        if (!level.isClientSide) {
            Vec3 look = player.getLookAngle();
            if (sneak) {
                player.setDeltaMovement(player.getDeltaMovement().subtract(look).scale(dashSpeed));
            } else {
                player.setDeltaMovement(player.getDeltaMovement().add(look).scale(dashSpeed));
            }
            player.hurtMarked = true;
            player.getCooldowns().addCooldown(this, 10);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
