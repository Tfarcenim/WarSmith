package tfar.warsmith.platform;

import com.chocohead.mm.api.ClassTinkerers;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.HitResult;
import tfar.warsmith.WarSmith;
import tfar.warsmith.network.C2SModPacket;
import tfar.warsmith.network.S2CModPacket;
import tfar.warsmith.platform.services.IPlatformHelper;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.function.Predicate;

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
    public EnchantmentCategory create(String name, Predicate<Item> predicate) {
        return ClassTinkerers.getEnum(EnchantmentCategory.class,name);
    }

    @Override
    public boolean fireProjectileImpactEvent(Projectile projectile, HitResult hitResult) {
        return false;
    }

    @Override
    public Attribute getEntityReachAttribute() {
        return ReachEntityAttributes.ATTACK_RANGE;
    }

    @Override
    public double getEntityReach(LivingEntity living) {
        return ReachEntityAttributes.getAttackRange(living,3);
    }

    @Override
    public Attribute getBlockReachAttribute() {
        return ReachEntityAttributes.REACH;
    }

    @Override
    public void sendToServer(C2SModPacket msg, ResourceLocation channel) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        msg.write(buf);
        ClientPlayNetworking.send(channel, buf);
    }

    @Override
    public void sendToClient(S2CModPacket msg, ResourceLocation channel, ServerPlayer player) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        msg.write(buf);
        ServerPlayNetworking.send(player, channel, buf);
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
