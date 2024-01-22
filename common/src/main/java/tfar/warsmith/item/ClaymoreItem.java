package tfar.warsmith.item;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import tfar.warsmith.platform.Services;

public class ClaymoreItem extends SwordItem {
    //2 handed
    public ClaymoreItem(Tier $$0, int damage, float attackSpeed, Properties $$3) {
        super($$0, damage, attackSpeed, $$3);
        $$3.durability(0);
        $$3.defaultDurability(1500);
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> superModifiers = ArrayListMultimap.create(super.getDefaultAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            superModifiers.put(Services.PLATFORM.getEntityReachAttribute(),
                    new AttributeModifier(WeaponItem.ENTITY_REACH_UUID, "Weapon Modifier", .5, AttributeModifier.Operation.MULTIPLY_TOTAL));
            superModifiers.put(Services.PLATFORM.getBlockReachAttribute(),
                    new AttributeModifier(WeaponItem.BLOCK_REACH_UUID, "Weapon Modifier", .5, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return superModifiers;
    }

}
