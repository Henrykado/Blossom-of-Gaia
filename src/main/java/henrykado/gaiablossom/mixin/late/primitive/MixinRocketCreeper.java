package henrykado.gaiablossom.mixin.late.primitive;

import net.daveyx0.primitivemobs.entity.monster.EntityRocketCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

import henrykado.gaiablossom.Config;

@Mixin(EntityRocketCreeper.class)
public abstract class MixinRocketCreeper extends EntityMob {

    public MixinRocketCreeper(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Override
    public boolean getCanSpawnHere() {
        if (this.dimension != 0 && Config.overworldOnlyPrimitiveMonsters) {
            return false;
        }
        if (!this.worldObj.isRaining() && Config.rocketCreeperRainSpawn) {
            return false;
        }
        return super.getCanSpawnHere();
    }
}
