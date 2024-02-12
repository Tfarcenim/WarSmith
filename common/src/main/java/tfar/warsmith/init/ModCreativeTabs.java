package tfar.warsmith.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab WARSMITH = CreativeModeTab.builder(null,-1)
            .title(Component.translatable("itemGroup.warsmith"))
            .icon(() -> new ItemStack(ModItems.IRON_KATANA))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.IRON_KATANA);
                output.accept(ModItems.DIAMOND_KATANA);
                output.accept(ModItems.NETHERITE_KATANA);
                output.accept(ModItems.IRON_KUSARIGAMA);
                output.accept(ModItems.DIAMOND_KUSARIGAMA);
                output.accept(ModItems.NETHERITE_KUSARIGAMA);
                output.accept(ModItems.IRON_SAI);
                output.accept(ModItems.DIAMOND_SAI);
                output.accept(ModItems.NETHERITE_SAI);
                output.accept(ModItems.CLAYMORE);
                output.accept(ModItems.IRON_HALBERD);
                output.accept(ModItems.DIAMOND_HALBERD);
                output.accept(ModItems.NETHERITE_HALBERD);
                output.accept(ModItems.IRON_BATTLEAXE);
                output.accept(ModItems.DIAMOND_BATTLEAXE);
                output.accept(ModItems.NETHERITE_BATTLEAXE);
                output.accept(ModItems.RAPIER);
                output.accept(ModItems.IRON_MACE);
                output.accept(ModItems.DIAMOND_MACE);
                output.accept(ModItems.NETHERITE_MACE);
                output.accept(ModItems.IRON_CUTLASS);
                output.accept(ModItems.DIAMOND_CUTLASS);
                output.accept(ModItems.NETHERITE_CUTLASS);
                output.accept(ModItems.BASEBALL_BAT);
                output.accept(ModItems.SPIKED_BASEBALL_BAT);
                output.accept(ModItems.NETHERITE_BASEBALL_BAT);
            })
            .build();
}
