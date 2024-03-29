package tfar.warsmith;

import com.google.common.collect.Multimap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.warsmith.duck.PlayerDuck;
import tfar.warsmith.enchantment.categories.ModEnchantmentCategories;
import tfar.warsmith.init.ModCreativeTabs;
import tfar.warsmith.init.ModEnchantments;
import tfar.warsmith.init.ModEntityTypes;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.item.KatanaItem;
import tfar.warsmith.item.MaceItem;
import tfar.warsmith.mixin.EnchantmentAccessor;
import tfar.warsmith.platform.Services;
import tfar.warsmith.tags.ModItemTags;

import java.util.HashMap;
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

    public static void afterRegistration() {
        ((EnchantmentAccessor) Enchantments.BANE_OF_ARTHROPODS).setCategory(ModEnchantmentCategories.BANE_OF_ARTHROPODS);
        ((EnchantmentAccessor) Enchantments.FIRE_ASPECT).setCategory(ModEnchantmentCategories.FIRE_ASPECT);
        ((EnchantmentAccessor) Enchantments.KNOCKBACK).setCategory(ModEnchantmentCategories.KNOCKBACK);
        ((EnchantmentAccessor) Enchantments.MOB_LOOTING).setCategory(ModEnchantmentCategories.LOOTING);
        ((EnchantmentAccessor) Enchantments.SHARPNESS).setCategory(ModEnchantmentCategories.SHARPNESS);
        ((EnchantmentAccessor) Enchantments.SMITE).setCategory(ModEnchantmentCategories.SMITE);
    }


    //  static UUID chest_negator = UUID.fromString("63476f76-f3c4-4bbe-8a0f-2822c66bd046");
    static UUID SHARPNESS_BOOST = UUID.fromString("5706c1c8-4801-4e43-b056-88e7213708f9");
    static UUID MACE_CHARGE = UUID.fromString("c92bf47f-83a2-4a73-9196-c9f72f9eca89");
    static UUID SELIGHT_OF_HAND_BOOST = UUID.fromString("be5eb80f-ef6a-4e51-ab9b-c1147ba2667e");

    public static float adjustDamage(LivingEntity livingEntity, DamageSource source, float base, float current) {
        Entity entity = source.getDirectEntity();
        float bonus = 0;
        float diff = base - current;
        if (entity instanceof LivingEntity living) {
            ItemStack hand = livingEntity.getMainHandItem();
            if (hand.getItem() instanceof KatanaItem) {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    ItemStack stack = livingEntity.getItemBySlot(slot);
                    if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getMaterial().equals(ArmorMaterials.LEATHER)) {
                        bonus += diff / 4;
                    }
                }
            }
        }
        return bonus;
    }

    public static void modifyAttributeModifiers(ItemStack stack, EquipmentSlot slot, Multimap<Attribute, AttributeModifier> attributeModifiers) {
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SLEIGHT_OF_HAND, stack) > 0 && slot == EquipmentSlot.MAINHAND) {
            attributeModifiers.get(Attributes.ATTACK_SPEED).add(new AttributeModifier(SELIGHT_OF_HAND_BOOST, "Sleight of Hand", 2, AttributeModifier.Operation.ADDITION));
        }

        if (slot == EquipmentSlot.MAINHAND && stack.getItem() instanceof MaceItem && MaceItem.isCharged(stack)) {
            attributeModifiers.get(Attributes.ATTACK_DAMAGE).add(new AttributeModifier(MACE_CHARGE, "Mace Charge", 3, AttributeModifier.Operation.ADDITION));
        }

        if (slot == EquipmentSlot.MAINHAND && isSharpened(stack)) {
            attributeModifiers.get(Attributes.ATTACK_DAMAGE).add(new AttributeModifier(SHARPNESS_BOOST, "Sharpened Boost", 3, AttributeModifier.Operation.ADDITION));
        }
    }

    static boolean isSharpened(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(SHARPENED_KEY);
    }

    public static final String SHARPENED_TOOLTIP_KEY = "warsmith.sharpened.tooltip";
    public static final String SHARPEN_TOOLTIP_KEY = "warsmith.sharpen.tooltip";
    public static final String SHARPENED_KEY = "sharpened";

    public static float getTotalMultipliers(float damage, LivingEntity attacker, Entity target) {
        float multiplier = 1;
        multiplier *= getSneakMultiplier(attacker, target);
        multiplier *= getOpportunisticMultiplier(attacker, target);
        multiplier *= getGoliathMultiplier(attacker, target);
        return multiplier;
    }

    public static void afterHit(Player player) {
        ItemStack weapon = player.getMainHandItem();
        if (isSharpened(weapon)) {
            if (!player.getAbilities().instabuild)
                useCharge(weapon);
        }
    }

    public static void useCharge(ItemStack stack) {
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            int uses = nbt.getInt(SHARPENED_KEY);
            uses--;
            if (uses > 0) {
                nbt.putInt(SHARPENED_KEY, uses);
            } else {
                nbt.remove(SHARPENED_KEY);
            }
        }
    }

    private static final float referenceVolume = EntityType.ENDERMAN.getWidth() * EntityType.ENDERMAN.getWidth() * EntityType.ENDERMAN.getHeight();

    public static float getGoliathMultiplier(LivingEntity attacker, Entity target) {
        if (target instanceof LivingEntity && EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.GOLIATH_FELLER, attacker.getMainHandItem()) > 0) {
            float volume = target.getBbHeight() * target.getBbWidth() * target.getBbWidth();
            if (volume >= referenceVolume) {
                return 1.2f;
            }
        }
        return 1;
    }


    public static float getClaymoreMultiplier(LivingEntity attacker, Entity target) {
        if (target instanceof LivingEntity living && attacker.getMainHandItem().is(ModItems.CLAYMORE)) {
            Iterable<ItemStack> armor = living.getArmorSlots();
            for (ItemStack stack : armor) {
                if (!stack.isEmpty()) {
                    return 1.4f;
                }
            }
        }
        return 1;
    }


    public static float getSneakMultiplier(LivingEntity attacker, Entity target) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SNEAK_ATTACK, attacker.getMainHandItem());
        if (level > 0) {

            Vec3 targetFacing = target.getLookAngle();
            Vec3 attackerPos = attacker.position();

            Vec3 attackerDirection = attackerPos.subtract(target.position()).normalize();

            if (attackerDirection == Vec3.ZERO) return 1;//no multiplier if the attacker is inside

            double angle = 180 / Math.PI * Math.acos(targetFacing.dot(attackerDirection));

            if (angle > 120) {
                if (attacker instanceof Player player) {
                    attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, attacker.getSoundSource(),
                            1.0F, 1.0F);
                    player.crit(target);
                }
                return 1.15f + .1f * level;
            }
        }
        return 1;
    }

    public static float blockDamage(LivingEntity living, float amount, DamageSource source) {
        return amount;
    }

    public static float getOpportunisticMultiplier(LivingEntity attacker, Entity target) {
        if (attacker instanceof Player player && target instanceof Player playerTarget) {
            if (((PlayerDuck) player).hasOpportunisticStrike()) {
                ((PlayerDuck) player).setOpportunisticStrike(false);
                if (playerTarget.getCooldowns().isOnCooldown(playerTarget.getMainHandItem().getItem())) {
                    return 1.5f;
                }
            }
        }
        return 1;
    }

    public static void removeItemEntities(List<Entity> items) {
        items.removeIf(ItemEntity.class::isInstance);
    }

    //return true to cancel
    public static boolean livingAttackEvent(LivingEntity target, DamageSource source, float amount) {
        if (target.isUsingItem()) {
            ItemStack using = target.getUseItem();
            if (using.is(ModItemTags.SAIS)) {
                Entity attacker = source.getDirectEntity();
                if (attacker instanceof Player livingAttacker) {
                    livingAttacker.getCooldowns().addCooldown(livingAttacker.getMainHandItem().getItem(), 20);
                    if (target instanceof Player playerTarget &&
                            EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.OPPORTUNISTIC_STRIKE, playerTarget.getMainHandItem()) > 0) {
                        ((PlayerDuck) playerTarget).setOpportunisticStrike(true);
                    }
                    return true;
                }
            }
        }
        return false;
    }


    public static void livingDamageEvent(LivingEntity livingEntity, DamageSource damageSource, float damageAmount) {
        Entity attacker = damageSource.getDirectEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            ItemStack stack = livingAttacker.getMainHandItem();
            if (stack.is(ModItemTags.HALBERDS)) {
                livingEntity.stopRiding();
            }

            if (stack.getItem() instanceof MaceItem && MaceItem.isCharged(stack)) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200));
                if (livingAttacker instanceof Player playerAttacker) {
                    playerAttacker.getCooldowns().addCooldown(stack.getItem(), 200);
                }
                MaceItem.unCharge(stack);
            }
        }
    }

    public static float armorMultiplier(DamageSource source) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof LivingEntity living) {
            ItemStack handStack = living.getMainHandItem();
            int pierceLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ARMOR_PIERCING, handStack);
            if (handStack.is(ModItems.RAPIER)) {
                pierceLevel += 1;
            }
            return (float) Math.pow(2, -pierceLevel);
        }
        return 1;
    }

    public static void onPlayerTouch(Player player, Entity touching) {
        ItemStack handStack = player.getMainHandItem();
        if (!player.level().isClientSide && handStack.is(ModItemTags.RAPIERS) && player.getCooldowns().isOnCooldown(handStack.getItem()) &&
                EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.PIERCING_DASH, handStack) > 0) {
            player.attack(touching);
        }
    }

    public static float onCriticalHit(Player player, Entity target, float damageModifier, boolean vanillaCritical) {
        if (vanillaCritical) {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.CRITICAL_STRIKE, player.getMainHandItem()) > 0) {
                return 2;
            }
            return damageModifier;
        }
        return 1;
    }

    public static final Map<EntityType<?>, Item> headMap = new HashMap<>();

    static {
        headMap.put(EntityType.WITHER_SKELETON, Items.WITHER_SKELETON_SKULL);
        headMap.put(EntityType.SKELETON, Items.SKELETON_SKULL);
        headMap.put(EntityType.ZOMBIE, Items.ZOMBIE_HEAD);
        headMap.put(EntityType.CREEPER, Items.CREEPER_HEAD);
        headMap.put(EntityType.PLAYER, Items.PLAYER_HEAD);
        headMap.put(EntityType.PIGLIN, Items.PIGLIN_HEAD);
    }

    public static void onDropCustomLoot(LivingEntity entity, DamageSource pDamageSource, int pLooting, boolean pHitByPlayer) {
        Entity attacker = pDamageSource.getEntity();

        if (attacker instanceof Player player && ((PlayerDuck) player).isChargedBaseballBat()) {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SKULL_CRUSHER, player) > 0) {
                Item skull = headMap.get(entity.getType());
                if (skull != null) {
                    ItemStack itemstack = new ItemStack(skull);
                    entity.spawnAtLocation(itemstack);
                }
            }
        }
    }

    public static void removeEnchantment(ItemStack stack, Enchantment enchantment) {
        ListTag enchs = stack.getEnchantmentTags();//compoundTag
        CompoundTag target = null;
        for (Tag tag : enchs) {
            CompoundTag compoundTag = (CompoundTag) tag;
            if (compoundTag.getString("id").equals(EnchantmentHelper.getEnchantmentId(enchantment).toString())) {
                target = compoundTag;
                break;
            }
        }
        if (target != null) {
            enchs.remove(target);
        }
    }

    public static void removeArmor(LivingEntity target, EquipmentSlot slot) {
        ItemStack held = target.getItemBySlot(slot);
        ItemEntity itemEntity = new ItemEntity(target.level(), target.getX(), target.getY(), target.getZ(), held);
        target.level().addFreshEntity(itemEntity);
        target.setItemSlot(slot, ItemStack.EMPTY);
    }

    public static void sharpenWeapon(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(SHARPENED_KEY, 15);
    }
}