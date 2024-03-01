package tfar.warsmith.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.GrindstoneMenu;
import tfar.warsmith.WarSmith;

public class C2SSharpenPacket implements C2SModPacket {

    public C2SSharpenPacket(FriendlyByteBuf buf) {

    }

    public C2SSharpenPacket() {

    }

    @Override
    public void handleServer(ServerPlayer player) {
        if (player.containerMenu instanceof GrindstoneMenu grindstoneMenu) {
            WarSmith.sharpenWeapon(grindstoneMenu.slots.get(0).getItem());
        }
    }

    @Override
    public void write(FriendlyByteBuf to) {
    }
}
