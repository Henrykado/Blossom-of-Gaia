package henrykado.gaiablossom.mixin.late.aether;

import net.minecraft.block.Block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.biome.AetherBiomeDecorator;

import henrykado.gaiablossom.Config;

@Mixin(AetherBiomeDecorator.class)
public abstract class MixinAetherBiomeDecorator {

    @Shadow
    public abstract void spawnOre(Block block, int size, int chance, int y);

    @Redirect(
        method = "genDecorations",
        at = @At(
            value = "INVOKE",
            target = "Lcom/gildedgames/the_aether/world/biome/AetherBiomeDecorator;spawnOre(Lnet/minecraft/block/Block;III)V"))
    public void spawnOreRedirect(AetherBiomeDecorator instance, Block block, int size, int chance, int y) {
        if (block == BlocksAether.gravitite_ore) {
            if (Config.gravititeOreSize == 0 || Config.gravititeOreChance == 0) return;
            spawnOre(block, Config.gravititeOreSize, Config.gravititeOreChance, Config.gravititeOreMaxY);
        } else if (block == BlocksAether.zanite_ore) {
            if (Config.zaniteOreSize == 0 || Config.zaniteOreChance == 0) return;
            spawnOre(block, Config.zaniteOreSize, Config.zaniteOreChance, Config.zaniteOreMaxY);
        } else if (block == BlocksAether.ambrosium_ore) {
            if (Config.ambrosiumOreSize == 0 || Config.ambrosiumOreChance == 0) return;
            spawnOre(block, Config.ambrosiumOreSize, Config.ambrosiumOreChance, Config.ambrosiumOreMaxY);
        } else if (block == BlocksAether.icestone) {
            if (Config.icestoneSize == 0 || Config.icestoneChance == 0) return;
            spawnOre(block, Config.icestoneSize, Config.icestoneChance, Config.icestoneMaxY);
        } else {
            spawnOre(block, size, chance, y);
        }
    }
}
