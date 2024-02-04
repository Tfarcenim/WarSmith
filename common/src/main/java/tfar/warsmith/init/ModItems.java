package tfar.warsmith.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import tfar.warsmith.item.*;

public class ModItems {

    public static final Item IRON_KATANA = new KatanaItem(Tiers.IRON,4,-2,new Item.Properties());
    public static final Item DIAMOND_KATANA = new KatanaItem(Tiers.DIAMOND,4,-2,new Item.Properties());
    public static final Item NETHERITE_KATANA = new KatanaItem(Tiers.NETHERITE,4,-2,new Item.Properties().fireResistant());
    public static final KusarigamaItem IRON_KUSARIGAMA = new KusarigamaItem(Tiers.IRON,3,-2.5,new Item.Properties());
    public static final KusarigamaItem DIAMOND_KUSARIGAMA = new KusarigamaItem(Tiers.DIAMOND,3,-2.5,new Item.Properties());
    public static final KusarigamaItem NETHERITE_KUSARIGAMA = new KusarigamaItem(Tiers.NETHERITE,3,-2.5,new Item.Properties().fireResistant());
    public static final Item IRON_SAI = new SaiItem(Tiers.IRON,2,-2.5,new Item.Properties());
    public static final Item DIAMOND_SAI = new SaiItem(Tiers.DIAMOND,2,-2.5,new Item.Properties());
    public static final Item NETHERITE_SAI = new SaiItem(Tiers.NETHERITE,2,-2.5,new Item.Properties().fireResistant());
    public static final Item CLAYMORE = new ClaymoreItem(Tiers.IRON,7,-2.8f,new Item.Properties());

    public static final Item IRON_HALBERD = new HalberdItem(Tiers.IRON,9,-3.2,new Item.Properties());
    public static final Item DIAMOND_HALBERD = new HalberdItem(Tiers.DIAMOND,9,-3.2,new Item.Properties());
    public static final Item NETHERITE_HALBERD = new HalberdItem(Tiers.NETHERITE,9,-3.2,new Item.Properties().fireResistant());

    public static final Item IRON_BATTLEAXE = new BattleAxeItem(Tiers.IRON,6,-3.2f,new Item.Properties());
    public static final Item DIAMOND_BATTLEAXE = new BattleAxeItem(Tiers.DIAMOND,5,-3.2f,new Item.Properties());
    public static final Item NETHERITE_BATTLEAXE = new BattleAxeItem(Tiers.NETHERITE,5,-3.2f,new Item.Properties().fireResistant());

   // public static final Item RAPIER = new

}
