package tfar.warsmith.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.warsmith.tags.ModItemTags;

@Mixin(Enchantment.class)
public class FireAspectEnchant {

    //note, this only allows for the weapon to get the enchant at the anvil, not the table
    @Inject(method = "canEnchant",at = @At("HEAD"),cancellable = true)
    private void kusirigmaEnchant(ItemStack $$0, CallbackInfoReturnable<Boolean> cir) {
        if (/*(Object)this == Enchantments.FIRE_ASPECT && */$$0.is(ModItemTags.KUSARIGAMAS)) {
            cir.setReturnValue(true);
        }
    }
}
