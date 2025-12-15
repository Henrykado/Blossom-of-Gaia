package henrykado.gaiablossom.mixin.early;

import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BiomeGenBase.class)
public class MixinBiomeGenBase {

    @Shadow
    protected List<net.minecraft.world.biome.BiomeGenBase.SpawnListEntry> spawnableMonsterList;

    @Shadow
    protected List<BiomeGenBase.SpawnListEntry> spawnableWaterCreatureList;

    @ModifyConstant(method = "<init>(IZ)V", constant = @Constant(intValue = 10, ordinal = 2))
    public int endermanSpawnWeight(int original) {
        return 15;
    }
}
