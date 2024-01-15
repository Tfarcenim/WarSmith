package tfar.warsmith.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import tfar.warsmith.WarSmith;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, WarSmith.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
    }
}
