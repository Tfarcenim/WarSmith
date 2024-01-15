package tfar.warsmith.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab WARSMITH = CreativeModeTab.builder(null,-1)
            .title(Component.translatable("itemGroup.warsmith"))
            .icon(() -> new ItemStack(ModItems.IRON_KATANA))

            .build();
}
