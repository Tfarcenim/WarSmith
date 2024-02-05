package tfar.warsmith.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import tfar.warsmith.init.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_KATANA)
                .pattern("  i")
                .pattern(" i ")
                .pattern("b  ")
                .define('i', Items.DIAMOND)
                .define('b',Items.BAMBOO)
                .unlockedBy("has_bamboo",has(Items.BAMBOO))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_KATANA)
                .pattern("  i")
                .pattern(" i ")
                .pattern("b  ")
                .define('i', Items.IRON_INGOT)
                .define('b',Items.BAMBOO)
                .unlockedBy("has_bamboo",has(Items.BAMBOO))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_KUSARIGAMA)
                .pattern("cii")
                .pattern("csc")
                .define('i', Items.IRON_INGOT)
                .define('s',Items.STICK)
                .define('c',Items.CHAIN)
                .unlockedBy("has_chain",has(Items.CHAIN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_KUSARIGAMA)
                .pattern("cii")
                .pattern("csc")
                .define('i', Items.DIAMOND)
                .define('s',Items.STICK)
                .define('c',Items.CHAIN)
                .unlockedBy("has_chain",has(Items.CHAIN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_SAI)
                .define('#', Items.STICK)
                .define('X', Items.IRON_INGOT)
                .pattern("XXX")
                .pattern(" # ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_SAI)
                .define('#', Items.STICK)
                .define('X', Items.DIAMOND)
                .pattern("XXX")
                .pattern(" # ")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_HALBERD)
                .pattern("Ii")
                .pattern("f ")
                .pattern("f ")
                .define('I', Items.IRON_AXE)
                .define('i', Items.IRON_INGOT)
                .define('f', ItemTags.WOODEN_FENCES)
                .unlockedBy("has_fences",has(ItemTags.WOODEN_FENCES))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_HALBERD)
                .pattern("Ii")
                .pattern("f ")
                .pattern("f ")
                .define('I', Items.DIAMOND_AXE)
                .define('i', Items.DIAMOND)
                .define('f', ItemTags.WOODEN_FENCES)
                .unlockedBy("has_fences",has(ItemTags.WOODEN_FENCES))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_BATTLEAXE)
                .pattern("ii")
                .pattern("fi")
                .pattern("s ")
                .define('s', Items.STICK)
                .define('i', Items.IRON_INGOT)
                .define('f', ItemTags.WOODEN_FENCES)
                .unlockedBy("has_fences",has(ItemTags.WOODEN_FENCES))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_BATTLEAXE)
                .pattern("ii")
                .pattern("fi")
                .pattern("s ")
                .define('s', Items.STICK)
                .define('i', Items.DIAMOND)
                .define('f', ItemTags.WOODEN_FENCES)
                .unlockedBy("has_fences",has(ItemTags.WOODEN_FENCES))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IRON_MACE)
                .pattern("iIi")
                .pattern(" s ")
                .pattern(" s ")
                .define('s', Items.STICK)
                .define('i', Items.IRON_INGOT)
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_iron_block",has(Items.IRON_BLOCK))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_MACE)
                .pattern("iIi")
                .pattern(" s ")
                .pattern(" s ")
                .define('s', Items.STICK)
                .define('i', Items.DIAMOND)
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_iron_block",has(Items.IRON_BLOCK))
                .save(pWriter);

    }
}
