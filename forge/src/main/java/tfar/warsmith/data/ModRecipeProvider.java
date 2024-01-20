package tfar.warsmith.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NETHERITE_KATANA)
                .pattern("  i")
                .pattern(" i ")
                .pattern("b  ")
                .define('i', Items.NETHERITE_INGOT)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NETHERITE_KUSARIGAMA)
                .pattern("cii")
                .pattern("csc")
                .define('i', Items.NETHERITE_INGOT)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.NETHERITE_SAI)
                .define('#', Items.STICK)
                .define('X', Items.NETHERITE_INGOT)
                .pattern("XXX")
                .pattern(" # ")
                .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(pWriter);
    }
}
