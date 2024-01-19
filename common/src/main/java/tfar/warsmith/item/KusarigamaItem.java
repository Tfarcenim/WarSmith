package tfar.warsmith.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import tfar.warsmith.duck.PlayerDuck;
import tfar.warsmith.entity.KusarigamaEntity;

public class KusarigamaItem extends WeaponItem {
    public KusarigamaItem(Tier $$0, float attackDamage, double attackSpeed, Properties $$1) {
        super($$0, attackDamage, attackSpeed, $$1);
    }

        /**
         * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
         * {@link #onItemUse}.
         */
        public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            PlayerDuck playerDuck = (PlayerDuck) pPlayer;
            if (playerDuck.getKusarigama() != null) {
                if (!pLevel.isClientSide) {
                    int i = playerDuck.getKusarigama().retrieve(itemstack);
                    itemstack.hurtAndBreak(i, pPlayer, (p_41288_) -> {
                        p_41288_.broadcastBreakEvent(pHand);
                    });
                }

                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                pPlayer.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
            } else {
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                if (!pLevel.isClientSide) {
                    pLevel.addFreshEntity(new KusarigamaEntity(pPlayer, pLevel));
                }

                pPlayer.awardStat(Stats.ITEM_USED.get(this));
                pPlayer.gameEvent(GameEvent.ITEM_INTERACT_START);
            }

            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        }
}
