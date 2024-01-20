package tfar.warsmith.client;

import net.fabricmc.api.ClientModInitializer;
import tfar.warsmith.platform.FabricClientHelper;
import tfar.warsmith.platform.Services;

public class ClientWarSmithFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Services.PLATFORM.setClientHelper(new FabricClientHelper());
        ClientMisc.clientSetup();
    }
}
