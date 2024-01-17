package tfar.warsmith.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import tfar.warsmith.item.KatanaItem;

public class ModItems {

    public static final Item IRON_KATANA = new KatanaItem(Tiers.IRON,4,-2,new Item.Properties());
    public static final Item DIAMOND_KATANA = new KatanaItem(Tiers.DIAMOND,4,-2,new Item.Properties());
    public static final Item NETHERITE_KATANA = new KatanaItem(Tiers.NETHERITE,4,-2,new Item.Properties());


}