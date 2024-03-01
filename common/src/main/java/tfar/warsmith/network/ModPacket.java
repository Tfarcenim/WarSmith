package tfar.warsmith.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import tfar.warsmith.WarSmith;

public interface ModPacket {

    ResourceLocation sharpen = new ResourceLocation(WarSmith.MOD_ID,"sharpen");
    void write(FriendlyByteBuf to);

}
