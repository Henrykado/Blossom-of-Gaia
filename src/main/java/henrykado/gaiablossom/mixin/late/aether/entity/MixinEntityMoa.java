package henrykado.gaiablossom.mixin.late.aether.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.gildedgames.the_aether.entities.passive.mountable.EntityMoa;

@Mixin(EntityMoa.class)
public class MixinEntityMoa {

    @Redirect(
        method = "onUpdate()V",
        at = @At(
            value = "INVOKE",
            target = "Lcom/gildedgames/the_aether/entities/passive/mountable/EntityMoa;isChild()Z"))
    public boolean preventEggDrop(EntityMoa instance) {
        return true;
    }
}
