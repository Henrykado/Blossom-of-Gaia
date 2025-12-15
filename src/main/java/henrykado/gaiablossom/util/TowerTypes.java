package henrykado.gaiablossom.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum TowerTypes {

    Null(Blocks.air, Blocks.air, Blocks.air, 0, Blocks.air),
    CobbleStone(Blocks.cobblestone, Blocks.torch, Blocks.double_stone_slab, 0, Blocks.stone_stairs),
    CobbleStoneMossy(Blocks.mossy_cobblestone, Blocks.torch, Blocks.double_stone_slab, 0, Blocks.stone_stairs),
    SandStone(Blocks.sandstone, Blocks.torch, Blocks.double_stone_slab, 1, Blocks.sandstone_stairs),
    Ice(Blocks.ice, Blocks.air, Blocks.clay, 2, Blocks.oak_stairs),
    SmoothStone(Blocks.stone, Blocks.torch, Blocks.double_stone_slab, 3, Blocks.stone_stairs),
    Netherrack(Blocks.netherrack, Blocks.glowstone, Blocks.soul_sand, 0, Blocks.nether_brick_stairs),
    Jungle(Blocks.mossy_cobblestone, Blocks.web, Blocks.dirt, 0, Blocks.jungle_stairs);

    private Block wallBlockID;
    private Block lightBlockID;
    private Block floorBlockID;
    private int floorBlockMetaData;
    private Block stairBlockID;

    private TowerTypes(Block a, Block b, Block c, int d, Block e) {
        this.wallBlockID = a;
        this.lightBlockID = b;
        this.floorBlockID = c;
        this.floorBlockMetaData = d;
        this.stairBlockID = e;
    }

    public Block getWallBlockID() {
        return this.wallBlockID;
    }

    public Block getLightBlockID() {
        return this.lightBlockID;
    }

    public Block getFloorBlockID() {
        return this.floorBlockID;
    }

    public int getFloorBlockMetaData() {
        return this.floorBlockMetaData;
    }

    public Block getStairBlockID() {
        return this.stairBlockID;
    }
}
