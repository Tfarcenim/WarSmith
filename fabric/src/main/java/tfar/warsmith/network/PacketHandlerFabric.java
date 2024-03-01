package tfar.warsmith.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Function;

public class PacketHandlerFabric {
    public static void registerMessages() {
        ServerPlayNetworking.registerGlobalReceiver(ModPacket.sharpen, wrapC2S(C2SSharpenPacket::new));
    }
    public static <MSG extends C2SModPacket> ServerPlayNetworking.PlayChannelHandler wrapC2S(Function<FriendlyByteBuf, MSG> decodeFunction) {
        return new ServerHandler<>(decodeFunction);
    }

}
