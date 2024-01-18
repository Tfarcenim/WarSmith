package tfar.warsmith;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.warsmith.init.ModCreativeTabs;
import tfar.warsmith.init.ModEnchantments;
import tfar.warsmith.init.ModEntityTypes;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.item.KatanaItem;
import tfar.warsmith.platform.Services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class WarSmith {

    public static final String MOD_ID = "warsmith";
    public static final String MOD_NAME = "WarSmith";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
    }


    public static void earlySetup() {
        Services.PLATFORM.superRegister(ModItems.class, BuiltInRegistries.ITEM, Item.class);
        Services.PLATFORM.superRegister(ModCreativeTabs.class, BuiltInRegistries.CREATIVE_MODE_TAB, CreativeModeTab.class);
        Services.PLATFORM.superRegister(ModEnchantments.class, BuiltInRegistries.ENCHANTMENT, Enchantment.class);
        Services.PLATFORM.superRegister(ModEntityTypes.class, BuiltInRegistries.ENTITY_TYPE, EntityType.class);

    }


    static UUID head_negator = UUID.fromString("aeb215b9-bf92-4083-87e7-de4932d0c5e6");
    static UUID chest_negator = UUID.fromString("63476f76-f3c4-4bbe-8a0f-2822c66bd046");
    static UUID leggings_negator = UUID.fromString("5706c1c8-4801-4e43-b056-88e7213708f9");
    static UUID boots_negator = UUID.fromString("c92bf47f-83a2-4a73-9196-c9f72f9eca89");
    static UUID SELIGHT_OF_HAND_BOOST = UUID.fromString("be5eb80f-ef6a-4e51-ab9b-c1147ba2667e");

    static UUID[] uuids = new UUID[]{boots_negator,leggings_negator,chest_negator,head_negator};

    public static float adjustDamage(LivingEntity livingEntity,DamageSource source,float base, float current) {
        Entity entity = source.getDirectEntity();
        float bonus = 0;
        float diff = base - current;
        if (entity instanceof LivingEntity living) {
            ItemStack hand = livingEntity.getMainHandItem();
            if (hand.getItem() instanceof KatanaItem) {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    ItemStack stack = livingEntity.getItemBySlot(slot);
                    if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getMaterial().equals(ArmorMaterials.LEATHER)) {
                        bonus += diff/4;
                    }
                }
            }
        }
        return bonus;
    }

    public static void modifyAttributeModifiers(ItemStack stack, EquipmentSlot slot, Multimap<Attribute, AttributeModifier> attributeModifiers) {
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SLEIGHT_OF_HAND,stack) > 0 && slot == EquipmentSlot.MAINHAND) {
            attributeModifiers.get(Attributes.ATTACK_SPEED).add(new AttributeModifier(SELIGHT_OF_HAND_BOOST, "Sleight of Hand",2, AttributeModifier.Operation.ADDITION));
        }
    }

    public static float getSneakMultiplier(LivingEntity attacker,Entity target) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SNEAK_ATTACK,attacker.getMainHandItem());
        if (level > 0) {

            Vec3 targetFacing = target.getLookAngle();
            Vec3 attackerPos = attacker.position();

            Vec3 attackerDirection = attackerPos.subtract(target.position()).normalize();

            if (attackerDirection == Vec3.ZERO) return 1;//no multiplier if the attacker is inside

            double angle = 180 / Math.PI * Math.acos(targetFacing.dot(attackerDirection));

            if (angle>120) {
                if (attacker instanceof Player player) {
                    attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, attacker.getSoundSource(),
                            1.0F, 1.0F);
                    player.crit(target);
                }
                return  1.15f + .1f * level;
            }
        }
        return 1;
    }

}