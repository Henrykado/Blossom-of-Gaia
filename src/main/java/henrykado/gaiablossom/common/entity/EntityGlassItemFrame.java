package henrykado.gaiablossom.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

import henrykado.gaiablossom.common.item.ModItems;

public class EntityGlassItemFrame extends EntityItemFrame {

    public EntityGlassItemFrame(World p_i1590_1_) {
        super(p_i1590_1_);
    }

    public EntityGlassItemFrame(World p_i1591_1_, int p_i1591_2_, int p_i1591_3_, int p_i1591_4_, int p_i1591_5_) {
        super(p_i1591_1_, p_i1591_2_, p_i1591_3_, p_i1591_4_, p_i1591_5_);
    }

    @Override
    public void func_146065_b(Entity p_146065_1_, boolean p_146065_2_) {
        ItemStack itemstack = this.getDisplayedItem();

        if (p_146065_1_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) p_146065_1_;

            if (entityplayer.capabilities.isCreativeMode) {
                this.removeFrameFromMap(itemstack);
                return;
            }
        }

        if (p_146065_2_) {
            this.entityDropItem(new ItemStack(ModItems.glass_item_frame), 0.0F);
        }

        if (itemstack != null) {
            itemstack = itemstack.copy();
            this.removeFrameFromMap(itemstack);
            this.entityDropItem(itemstack, 0.0F);
        }
    }

    private void removeFrameFromMap(ItemStack p_110131_1_) {
        if (p_110131_1_ != null) {
            if (p_110131_1_.getItem() == Items.filled_map) {
                MapData mapdata = ((ItemMap) p_110131_1_.getItem()).getMapData(p_110131_1_, this.worldObj);
                mapdata.playersVisibleOnMap.remove("frame-" + this.getEntityId());
            }

            p_110131_1_.setItemFrame((EntityItemFrame) null);
        }
    }
}
