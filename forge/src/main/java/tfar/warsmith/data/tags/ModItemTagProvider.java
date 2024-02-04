package tfar.warsmith.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tfar.warsmith.WarSmith;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, WarSmith.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModItemTags.KATANAS).add(ModItems.IRON_KATANA,ModItems.DIAMOND_KATANA,ModItems.NETHERITE_KATANA);
        tag(ModItemTags.KUSARIGAMAS).add(ModItems.IRON_KUSARIGAMA,ModItems.DIAMOND_KUSARIGAMA,ModItems.NETHERITE_KUSARIGAMA);
        tag(ModItemTags.SAIS).add(ModItems.IRON_SAI,ModItems.DIAMOND_SAI,ModItems.NETHERITE_SAI);
        tag(ModItemTags.HALBERDS).add(ModItems.IRON_HALBERD,ModItems.DIAMOND_HALBERD,ModItems.NETHERITE_HALBERD);
        tag(ModItemTags.BATTLEAXES).add(ModItems.IRON_BATTLEAXE,ModItems.DIAMOND_BATTLEAXE,ModItems.NETHERITE_BATTLEAXE);

        tag(ModItemTags.CAN_APPLY_FIRE_ASPECT).addTags(ItemTags.SWORDS,ModItemTags.KUSARIGAMAS);
        tag(ModItemTags.CAN_APPLY_KNOCKBACK).addTags(ItemTags.SWORDS,ModItemTags.BATTLEAXES);

        tag(ItemTags.TOOLS).addTags(ModItemTags.KATANAS,ModItemTags.KUSARIGAMAS,ModItemTags.SAIS,ModItemTags.HALBERDS,ModItemTags.BATTLEAXES);
    }
}
