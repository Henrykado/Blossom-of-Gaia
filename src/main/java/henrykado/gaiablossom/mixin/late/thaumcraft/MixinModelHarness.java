package henrykado.gaiablossom.mixin.late.thaumcraft;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import thaumcraft.client.renderers.models.gear.ModelHoverHarness;

@Mixin(ModelHoverHarness.class)
public class MixinModelHarness extends ModelBiped {

    @Inject(method = "func_78088_a", at = @At("HEAD"), cancellable = true)
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7,
        CallbackInfo ci) {
        GL11.glPushMatrix();
        if (entity != null && entity.isSneaking()) {
            GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
        }

        this.bipedBody.render(par7);
        GL11.glPopMatrix();
        ci.cancel();
    }
}
