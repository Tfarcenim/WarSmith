package tfar.warsmith.platform.services;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.HitResult;
import tfar.warsmith.platform.ClientHelper;

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

    EnchantmentCategory create(String name,TagKey<Item> tagKey);

    boolean fireProjectileImpactEvent(Projectile projectile, HitResult hitResult);

    Attribute getEntityReachAttribute();
    ClientHelper getClientHelper();

    void setClientHelper(ClientHelper helper);


}