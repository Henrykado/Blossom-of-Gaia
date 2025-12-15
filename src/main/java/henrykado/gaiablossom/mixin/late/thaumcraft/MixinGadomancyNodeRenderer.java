package henrykado.gaiablossom.mixin.late.thaumcraft;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import baubles.api.expanded.BaubleExpandedSlots;
import henrykado.gaiablossom.util.BaublesUtils;
import makeo.gadomancy.client.renderers.tile.RenderTileNodeBasic;
import thaumcraft.api.nodes.IRevealer;

@Mixin(value = RenderTileNodeBasic.class, remap = false)
public class MixinGadomancyNodeRenderer {

    @ModifyArgs(
        method = "renderTileEntityAt(Lnet/minecraft/tileentity/TileEntity;DDDFF)V",
        at = @At(
            value = "INVOKE",
            target = "Lmakeo/gadomancy/client/renderers/tile/RenderTileNodeBasic;renderNode(Lnet/minecraft/entity/EntityLivingBase;DZZFDDDFLthaumcraft/api/aspects/AspectList;Lthaumcraft/api/nodes/NodeType;Lthaumcraft/api/nodes/NodeModifier;)V"),
        remap = false)
    private static void renderNodeInject(Args args) {
        EntityLivingBase viewer = args.get(0);
        boolean visible = args.get(2);
        boolean depthIgnore = args.get(3);
        if (viewer instanceof EntityPlayer && (!visible || !depthIgnore)) {
            ItemStack stack = BaublesUtils
                .getStackInFirstSlotOfType((EntityPlayer) viewer, BaubleExpandedSlots.headType);
            if (stack != null && stack.getItem() instanceof IRevealer) {
                args.set(2, true);
                args.set(3, true);
            }
        }
    }
}
