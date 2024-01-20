package tfar.warsmith.item;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import tfar.warsmith.platform.Services;

public class SaiItem extends WeaponItem {
    public SaiItem(Tier $$0, float attackDamage, double attackSpeed, Properties $$1) {
        super($$0, attackDamage, attackSpeed, $$1, Handedness.ONE_HAND);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> superModifiers = ArrayListMultimap.create(super.getDefaultAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            superModifiers.put(Services.PLATFORM.getEntityReachAttribute(),
                    new AttributeModifier(ENTITY_REACH_UUID, "Weapon Modifier", -.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return superModifiers;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }


    @Override
    public UseAnim getUseAnimation(ItemStack $$0) {
        return UseAnim.BLOCK;
    }

}
