package henrykado.gaiablossom.mixin.late.aether;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.blocks.natural.BlockAetherLog;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.items.tools.ItemSkyrootTool;

@Mixin(BlockAetherLog.class)
public abstract class MixinBlockAetherLog extends Block {

    protected MixinBlockAetherLog(Material materialIn) {
        super(materialIn);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void harvestBlock(World worldIn, EntityPlayer player, int x, int y, int z, int meta) {
        player.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
        player.addExhaustion(0.025F);
        int size = meta == 0 ? 2 : 1;
        ItemStack stack = player.getCurrentEquippedItem();
        if (this.canSilkHarvest(worldIn, player, x, y, z, meta) && EnchantmentHelper.getSilkTouchModifier(player)) {
            ArrayList<ItemStack> items = new ArrayList();
            ItemStack itemstack = this.createStackedBlock(meta);
            if (itemstack != null) {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, worldIn, this, x, y, z, meta, 0, 1.0F, true, player);
            Iterator var11 = items.iterator();

            while (var11.hasNext()) {
                ItemStack is = (ItemStack) var11.next();
                this.dropBlockAsItem(worldIn, x, y, z, is);
            }
        } else if (stack != null) {
            if (stack.getItem() instanceof ItemSkyrootTool) {
                for (int i = 0; i < size; ++i) {
                    this.dropBlockAsItem(
                        player.worldObj,
                        x,
                        y,
                        z,
                        meta,
                        EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
                }
            }
            if (this == BlocksAether.golden_oak_log) {
                this.dropBlockAsItem(
                    worldIn,
                    x,
                    y,
                    z,
                    new ItemStack(ItemsAether.golden_amber, worldIn.rand.nextInt(3) == 0 ? 1 : 0));
            }
        } else {
            super.harvestBlock(worldIn, player, x, y, z, meta);
        }
    }
}
