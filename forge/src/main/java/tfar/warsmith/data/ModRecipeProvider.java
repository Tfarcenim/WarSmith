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
    }
}
