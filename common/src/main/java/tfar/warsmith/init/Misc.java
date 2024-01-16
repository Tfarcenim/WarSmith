package tfar.warsmith.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import tfar.warsmith.WarSmith;

public class Misc {

    public static final ResourceKey<DamageType> ON_SOUL_FIRE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(WarSmith.MOD_ID,"on_soul_fire"));


}
