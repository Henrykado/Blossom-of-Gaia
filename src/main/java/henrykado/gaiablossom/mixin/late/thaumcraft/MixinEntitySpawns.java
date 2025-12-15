package henrykado.gaiablossom.mixin.late.thaumcraft;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import ganymedes01.etfuturum.configuration.configs.ConfigExperiments;
import ganymedes01.etfuturum.world.nether.biome.utils.NetherBiomeManager;
import thaumcraft.common.config.ConfigEntities;

@Mixin(ConfigEntities.class)
public abstract class MixinEntitySpawns {

    @Redirect(
        method = "initEntitySpawns",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/common/BiomeDictionary;getBiomesForType(Lnet/minecraftforge/common/BiomeDictionary$Type;)[Lnet/minecraft/world/biome/BiomeGenBase;"),
        remap = false)
    private static BiomeGenBase[] getBiomeForTypeRedirect(BiomeDictionary.Type type) {
        if (type == BiomeDictionary.Type.NETHER && ConfigExperiments.netherDimensionProvider) {
            return new BiomeGenBase[] { BiomeGenBase.hell, NetherBiomeManager.soulSandValley };
        }
        return BiomeDictionary.getBiomesForType(type);
    }
}
