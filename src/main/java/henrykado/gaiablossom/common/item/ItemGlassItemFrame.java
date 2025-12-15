package henrykado.gaiablossom.common.item;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import henrykado.gaiablossom.common.entity.EntityGlassItemFrame;

public class ItemGlassItemFrame extends ModItem {

    public ItemGlassItemFrame() {
        super("glass_itemframe");
    }

    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_,
        int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (p_77648_7_ == 0) {
            return false;
        } else if (p_77648_7_ == 1) {
            return false;
        } else {
            int i1 = Direction.facingToDirection[p_77648_7_];
            EntityHanging entityhanging = new EntityGlassItemFrame(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1);

            if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_)) {
                return false;
            } else {
                if (entityhanging != null && entityhanging.onValidSurface()) {
                    if (!p_77648_3_.isRemote) {
                        p_77648_3_.spawnEntityInWorld(entityhanging);
                    }

                    --p_77648_1_.stackSize;
                }

                return true;
            }
        }
    }
}
