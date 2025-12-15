package henrykado.gaiablossom.mixin.late;

import java.util.ArrayList;

import net.minecraft.block.BlockBush;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import org.spongepowered.asm.mixin.Mixin;

import ganymedes01.etfuturum.blocks.BlockNetherRoots;

@Mixin(BlockNetherRoots.class)
public class MixinNetherRoots extends BlockBush implements IShearable {

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        return new ArrayList<ItemStack>();
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }
}
