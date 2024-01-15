package tfar.warsmith;

import net.minecraftforge.fml.common.Mod;

@Mod(WarSmith.MOD_ID)
public class WarSmithForge {
    
    public WarSmithForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        WarSmith.LOG.info("Hello Forge world!");
        WarSmith.init();
        
    }
}