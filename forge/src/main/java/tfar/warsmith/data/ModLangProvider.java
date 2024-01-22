package tfar.warsmith.data;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;
import tfar.warsmith.WarSmith;
import tfar.warsmith.init.ModCreativeTabs;
import tfar.warsmith.init.ModEnchantments;
import tfar.warsmith.init.ModItems;

import java.util.function.Supplier;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, WarSmith.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addDefaultItem(() -> ModItems.DIAMOND_KATANA);
        addDefaultItem(() -> ModItems.IRON_KATANA);
        addDefaultItem(() -> ModItems.NETHERITE_KATANA);
        addDefaultItem(() -> ModItems.IRON_KUSARIGAMA);
        addDefaultItem(() -> ModItems.DIAMOND_KUSARIGAMA);
        addDefaultItem(() -> ModItems.NETHERITE_KUSARIGAMA);
        addDefaultItem(() -> ModItems.IRON_SAI);
        addDefaultItem(() -> ModItems.DIAMOND_SAI);
        addDefaultItem(() -> ModItems.NETHERITE_SAI);
        addDefaultItem(() -> ModItems.CLAYMORE);

        addDefaultEnchantment(() -> ModEnchantments.AMATERASU);
        addDefaultEnchantment(() -> ModEnchantments.SLEIGHT_OF_HAND);
        addDefaultEnchantment(() -> ModEnchantments.SNEAK_ATTACK);
        addDefaultEnchantment(() -> ModEnchantments.THIEVING_CHAIN);
        addDefaultEnchantment(() -> ModEnchantments.OPPORTUNISTIC_STRIKE);
        addDefaultEnchantment(() -> ModEnchantments.TOXIC_ADVANTAGE);

        addTab(ModCreativeTabs.WARSMITH,WarSmith.MOD_NAME);
    }


    protected void addTab(CreativeModeTab tab, String translation) {
        Component display  = tab.getDisplayName();
        ComponentContents contents = display.getContents();
        if (contents instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(), translation);
        } else {
            throw new RuntimeException("Not translatable: "+tab);
        }
    }

    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier,getNameFromItem(supplier.get()));
    }

    protected void addDefaultBlock(Supplier<? extends Block> supplier) {
        addBlock(supplier,getNameFromBlock(supplier.get()));
    }

    protected void addDefaultEnchantment(Supplier<? extends Enchantment> supplier) {
        addEnchantment(supplier,getNameFromEnchantment(supplier.get()));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEnchantment(Enchantment enchantment) {
        return StringUtils.capitaliseAllWords(enchantment.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

}
