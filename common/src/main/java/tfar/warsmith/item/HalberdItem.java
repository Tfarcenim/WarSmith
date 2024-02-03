package tfar.warsmith.item;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Tier;
import tfar.warsmith.platform.Services;

public class HalberdItem extends WeaponItem {
    public HalberdItem(Tier $$0, float attackDamage, double attackSpeed, Properties $$1) {
        super($$0, attackDamage, attackSpeed, $$1,Handedness.TWO_HAND);
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> superModifiers = ArrayListMultimap.create(super.getDefaultAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            superModifiers.put(Services.PLATFORM.getEntityReachAttribute(),
                    new AttributeModifier(WeaponItem.ENTITY_REACH_UUID, "Weapon Modifier",3, AttributeModifier.Operation.ADDITION));//player default is 3
            superModifiers.put(Services.PLATFORM.getBlockReachAttribute(),
                    new AttributeModifier(WeaponItem.BLOCK_REACH_UUID, "Weapon Modifier", 4.5, AttributeModifier.Operation.ADDITION));//player default is 4.5
        }
        return superModifiers;
    }

}
