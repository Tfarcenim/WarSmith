package tfar.warsmith.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class MaceItem extends WeaponItem {
    public MaceItem(Tier $$0, float attackDamage, double attackSpeed, Properties $$1) {
        super($$0, attackDamage, attackSpeed, $$1, Handedness.ONE_HAND);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            charge(stack);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public boolean isFoil(ItemStack $$0) {
        return super.isFoil($$0) || isCharged($$0);
    }

    public static boolean isCharged(ItemStack stack) {
        return stack.hasTag() && stack.getTag().getBoolean("charged");
    }

    public static void charge(ItemStack stack) {
        stack.getOrCreateTag().putBoolean("charged",true);
    }

    public static void unCharge(ItemStack stack) {
        stack.getOrCreateTag().remove("charged");
    }

}
