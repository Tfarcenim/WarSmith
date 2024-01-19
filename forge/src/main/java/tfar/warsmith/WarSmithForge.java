package tfar.warsmith;

import com.google.common.collect.Multimap;
import com.google.gson.internal.reflect.ReflectionHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import tfar.warsmith.client.ClientMisc;
import tfar.warsmith.data.Datagen;
import tfar.warsmith.platform.ForgeClientHelper;
import tfar.warsmith.platform.Services;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod(WarSmith.MOD_ID)
public class WarSmithForge {
    
    public WarSmithForge() {

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Method modifableMapMethod = null;
        try {
            modifableMapMethod = ItemAttributeModifierEvent.class.getDeclaredMethod("getModifiableMap");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        modifableMapMethod.setAccessible(true);
        try {
            methodHandle = lookup.unreflect(modifableMapMethod);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        IEventBus bus  = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(Datagen::gather);
        bus.addListener(this::commonSetup);

        if (FMLEnvironment.dist.isClient()) {
            Services.PLATFORM.setClientHelper(new ForgeClientHelper());
            bus.addListener(this::clientSetup);
        }

        MinecraftForge.EVENT_BUS.addListener(this::onAttributeModified);
        WarSmith.init();
        WarSmith.earlySetup();
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        WarSmith.afterRegistration();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ClientMisc.registerEntityRenderers();
    }

    public static MethodHandle methodHandle;

    private void onAttributeModified(ItemAttributeModifierEvent event) {
        try {
            Multimap<Attribute,AttributeModifier> map = (Multimap<Attribute, AttributeModifier>) methodHandle.invoke(event);
            WarSmith.modifyAttributeModifiers(event.getItemStack(),event.getSlotType(),map);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Registry<?>, List<Pair<ResourceLocation, Supplier<?>>>> registerLater = new HashMap<>();
    private void register(RegisterEvent e) {
        for (Map.Entry<Registry<?>,List<Pair<ResourceLocation, Supplier<?>>>> entry : registerLater.entrySet()) {
            Registry<?> registry = entry.getKey();
            List<Pair<ResourceLocation, Supplier<?>>> toRegister = entry.getValue();
            for (Pair<ResourceLocation,Supplier<?>> pair : toRegister) {
                e.register((ResourceKey<? extends Registry<Object>>)registry.key(),pair.getLeft(),(Supplier<Object>)pair.getValue());
            }
        }
    }
}