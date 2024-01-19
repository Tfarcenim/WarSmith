package tfar.warsmith.platform;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;
import org.apache.commons.lang3.tuple.Pair;
import tfar.warsmith.WarSmith;
import tfar.warsmith.WarSmithForge;
import tfar.warsmith.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    public ClientHelper CLIENT_HELPER;
    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends Registry<? extends F>,F> void superRegister(Class<?> clazz, T registry, Class<F> filter) {
        List<Pair<ResourceLocation, Supplier<?>>> list = WarSmithForge.registerLater.computeIfAbsent(registry, k -> new ArrayList<>());
        for (Field field : clazz.getFields()) {
            MappedRegistry<? extends F> forgeRegistry = (MappedRegistry<? extends F>) registry;
            forgeRegistry.unfreeze();
            try {
                Object o = field.get(null);
                if (filter.isInstance(o)) {
                    list.add(Pair.of(new ResourceLocation(WarSmith.MOD_ID,field.getName().toLowerCase(Locale.ROOT)),() -> o));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    @Override
    public EnchantmentCategory create(String name, Predicate<Item> predicate) {
        return EnchantmentCategory.create(name,predicate);
    }

    @Override
    public Attribute getEntityReachAttribute() {
        return ForgeMod.ENTITY_REACH.get();
    }

    @Override
    public ClientHelper getClientHelper() {
        return CLIENT_HELPER;
    }

    @Override
    public boolean fireProjectileImpactEvent(Projectile projectile, HitResult hitResult) {
        return net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(projectile,hitResult);
    }

    @Override
    public void setClientHelper(ClientHelper helper) {
        CLIENT_HELPER = helper;
    }
}