package henrykado.gaiablossom.mixin.early.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderWolf.class)
public abstract class MixinRenderWolf extends Render {

    @Unique
    private static final ResourceLocation blossomOfGaia$alfredoWolfTextures = new ResourceLocation(
        "gaiablossom",
        "textures/alfredo.png");

    @Unique
    private static final ResourceLocation blossomOfGaia$alfredoAngryWolfTextures = new ResourceLocation(
        "gaiablossom",
        "textures/alfredo_angry.png");

    @Unique
    private static final ResourceLocation blossomOfGaia$alfredoCollarTextures = new ResourceLocation(
        "gaiablossom",
        "textures/alfredo_collar.png");

    @Inject(
        method = "getEntityTexture(Lnet/minecraft/entity/passive/EntityWolf;)Lnet/minecraft/util/ResourceLocation;",
        at = @At(value = "RETURN"),
        cancellable = true)
    private void getEntityTextureInject(EntityWolf wolf, CallbackInfoReturnable<ResourceLocation> cir) {
        if (EnumChatFormatting.getTextWithoutFormattingCodes(wolf.getCommandSenderName())
            .equals("Alfredo")) {
            cir.setReturnValue(
                wolf.isTamed() ? blossomOfGaia$alfredoWolfTextures
                    : (wolf.isAngry() ? blossomOfGaia$alfredoAngryWolfTextures : blossomOfGaia$alfredoWolfTextures));
        }
        cir.setReturnValue(cir.getReturnValue());
    }

    @Shadow
    protected abstract int shouldRenderPass(EntityWolf p_77032_1_, int p_77032_2_, float p_77032_3_);

    @Inject(method = "shouldRenderPass", at = @At("HEAD"), cancellable = true)
    public void otherTextures(EntityWolf wolf, int i, float f, CallbackInfoReturnable<Integer> cir) {
        if (EnumChatFormatting.getTextWithoutFormattingCodes(wolf.getCommandSenderName())
            .equals("Alfredo")) {
            if (i == 0 && wolf.getWolfShaking()) {
                float f1 = wolf.getBrightness(f) * wolf.getShadingWhileShaking(f);
                this.bindTexture(blossomOfGaia$alfredoWolfTextures);
                GL11.glColor3f(f1, f1, f1);
                cir.setReturnValue(1);
            } else if (i == 1 && wolf.isTamed()) {
                this.bindTexture(blossomOfGaia$alfredoCollarTextures);
                cir.setReturnValue(1);
            }
        }
    }
}
