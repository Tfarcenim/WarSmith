package tfar.warsmith.platform;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.HitResult;
import tfar.warsmith.WarSmith;
import tfar.warsmith.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Field;
import java.util.Locale;

public class FabricPlatformHelper implements IPlatformHelper {

    public ClientHelper CLIENT_HELPER;
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends Registry<? extends F>,F> void superRegister(Class<?> clazz, T registry, Class<F> filter) {
        for (Field field : clazz.getFields()) {
            try {
                Object o = field.get(null);
                if (filter.isInstance(o)) {
                    Registry.register((Registry<? super F>) registry,new ResourceLocation(WarSmith.MOD_ID,field.getName().toLowerCase(Locale.ROOT)),(F)o);
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    @Override
    public EnchantmentCategory create(String name, TagKey<Item> tagKey) {
        return ClassTinkerers.getEnum(EnchantmentCategory.class,name);
    }

    @Override
    public boolean fireProjectileImpactEvent(Projectile projectile, HitResult hitResult) {
        return false;
    }

    @Override
    public ClientHelper getClientHelper() {
        return CLIENT_HELPER;
    }

    @Override
    public void setClientHelper(ClientHelper helper) {
        CLIENT_HELPER = helper;
    }
}
