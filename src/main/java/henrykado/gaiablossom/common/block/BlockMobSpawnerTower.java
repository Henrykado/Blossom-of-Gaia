package henrykado.gaiablossom.common.block;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import henrykado.gaiablossom.common.block.tile.TileEntityMobSpawnerTower;

public class BlockMobSpawnerTower extends BlockMobSpawner {

    public BlockMobSpawnerTower() {
        this.setHardness(5.0F)
            .setStepSound(soundTypeMetal);

        String name = "towerMobSpawner";

        setBlockName(name);
        setBlockTextureName("mob_spawner");
        GameRegistry.registerBlock(this, name);

        setCreativeTab(CreativeTabs.tabAllSearch);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMobSpawnerTower();
    }
}
