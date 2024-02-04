package tfar.warsmith.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class BattleAxeItem extends AxeItem {
    //1 handed
    public BattleAxeItem(Tier $$0, float $$1, float $$2, Properties $$3) {
        super($$0, $$1, $$2, $$3);
    }

    @Override
    public boolean hurtEnemy(ItemStack $$0, LivingEntity $$1, LivingEntity $$2) {
        $$0.hurtAndBreak(1, $$2, $$0x -> $$0x.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
