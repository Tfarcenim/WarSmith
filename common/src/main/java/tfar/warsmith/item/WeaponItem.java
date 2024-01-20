package tfar.warsmith.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;

import java.util.UUID;

public class WeaponItem extends TieredItem {
    public static final UUID ENTITY_REACH_UUID = UUID.fromString("d9151dd5-1c86-4294-b5e0-802705497a65");

    private final float attackDamage;
    protected final Multimap<Attribute, AttributeModifier> defaultModifiers;
    private final Handedness handedness;

    public WeaponItem(Tier $$0, float attackDamage, double attackSpeed, Properties $$1,Handedness handedness) {
        super($$0, $$1);
        this.handedness = handedness;
        this.attackDamage = attackDamage + $$0.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> $$4 = ImmutableMultimap.builder();
        $$4.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION)
        );
        $$4.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = $$4.build();
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public enum Handedness {
        ONE_HAND,TWO_HAND;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot $$0) {
        return $$0 == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers($$0);
    }
}
