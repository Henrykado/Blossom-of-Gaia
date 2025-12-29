package henrykado.gaiablossom.mixin.late.thaumcraft;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import henrykado.gaiablossom.Config;
import thaumcraft.common.lib.world.WorldGenGreatwoodTrees;

@Mixin(WorldGenGreatwoodTrees.class)
public abstract class MixinWorldGenGreatwood extends WorldGenerator {

    @Shadow
    private World worldObj;

    @Inject(method = "validTreeLocation", at = @At("HEAD"), remap = false, cancellable = true)
    public void validTreeLocationInject(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        if (worldObj.provider.dimensionId != 0 && Config.overworldOnlyGreatwood) cir.setReturnValue(false);

        for (int i = 0; i < Config.greatwoodBiomeIDBlacklist.length; i++) {
            if (worldObj.getBiomeGenForCoords(x, z).biomeID == Integer.parseInt(Config.greatwoodBiomeIDBlacklist[i])) {
                cir.setReturnValue(false);
            }
        }
    }
}
