package tfar.warsmith;

import com.google.common.collect.Multimap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import tfar.warsmith.init.ModItems;
import tfar.warsmith.tags.ModItemTags;

public class WarSmithFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        WarSmith.earlySetup();

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.
        ModifyItemAttributeModifiersCallback.EVENT.register(this::modifyAttributeModifiers);
        // Use Fabric to bootstrap the Common mod.
        WarSmith.init();
        WarSmith.afterRegistration();

        VillagerTrades.ItemsForEmeralds trade = new VillagerTrades.ItemsForEmeralds(ModItems.CLAYMORE, 26, 3, 1);

        VillagerTrades.ItemsForEmeralds trade1 = new VillagerTrades.ItemsForEmeralds(ModItems.RAPIER, 34, 3, 1);

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,1,itemListings -> {
            itemListings.add(trade);
            itemListings.add(trade1);
        });
    }

    void modifyAttributeModifiers(ItemStack stack, EquipmentSlot slot, Multimap<Attribute, AttributeModifier> attributeModifiers) {
        WarSmith.modifyAttributeModifiers(stack, slot, attributeModifiers);
    }

    public static boolean forceSweep(Player player, boolean original, boolean fullAttackStrength, boolean bl1, boolean bl2) {
        if (original) return true;

        return fullAttackStrength && !bl1 && !bl2 && player.getMainHandItem().is(ModItemTags.BATTLEAXES);
    }
}
