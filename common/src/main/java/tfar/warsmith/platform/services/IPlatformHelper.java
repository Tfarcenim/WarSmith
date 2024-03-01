package tfar.warsmith.platform.services;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.HitResult;
import tfar.warsmith.network.C2SModPacket;
import tfar.warsmith.network.S2CModPacket;
import tfar.warsmith.platform.ClientHelper;

import java.util.function.Predicate;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    <T extends Registry<? extends F>,F> void superRegister(Class<?> clazz, T registry, Class<F> filter);

    default EnchantmentCategory create(String name,TagKey<Item> tagKey) {
        return create(name,item -> item.builtInRegistryHolder().is(tagKey));
    }
    EnchantmentCategory create(String name, Predicate<Item> predicate);

    boolean fireProjectileImpactEvent(Projectile projectile, HitResult hitResult);

    Attribute getEntityReachAttribute();
    double getEntityReach(LivingEntity living);

    Attribute getBlockReachAttribute();

    void sendToServer(C2SModPacket msg, ResourceLocation channel);
    void sendToClient(S2CModPacket msg, ResourceLocation channel, ServerPlayer player);


    ClientHelper getClientHelper();

    void setClientHelper(ClientHelper helper);


}