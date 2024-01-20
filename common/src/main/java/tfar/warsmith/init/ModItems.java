package tfar.warsmith.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import tfar.warsmith.item.KatanaItem;
import tfar.warsmith.item.KusarigamaItem;
import tfar.warsmith.item.SaiItem;

public class ModItems {

    public static final Item IRON_KATANA = new KatanaItem(Tiers.IRON,4,-2,new Item.Properties());
    public static final Item DIAMOND_KATANA = new KatanaItem(Tiers.DIAMOND,4,-2,new Item.Properties());
    public static final Item NETHERITE_KATANA = new KatanaItem(Tiers.NETHERITE,4,-2,new Item.Properties());
    public static final KusarigamaItem IRON_KUSARIGAMA = new KusarigamaItem(Tiers.IRON,3,-2.5,new Item.Properties());
    public static final KusarigamaItem DIAMOND_KUSARIGAMA = new KusarigamaItem(Tiers.DIAMOND,3,-2.5,new Item.Properties());
    public static final KusarigamaItem NETHERITE_KUSARIGAMA = new KusarigamaItem(Tiers.NETHERITE,3,-2.5,new Item.Properties());
    public static final Item IRON_SAI = new SaiItem(Tiers.IRON,2,-2.5,new Item.Properties());
    public static final Item DIAMOND_SAI = new SaiItem(Tiers.DIAMOND,2,-2.5,new Item.Properties());
    public static final Item NETHERITE_SAI = new SaiItem(Tiers.NETHERITE,2,-2.5,new Item.Properties());


}
