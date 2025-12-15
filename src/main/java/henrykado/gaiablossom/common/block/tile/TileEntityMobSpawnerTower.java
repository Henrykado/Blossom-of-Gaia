package henrykado.gaiablossom.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import henrykado.gaiablossom.common.block.ModBlock;

public class TileEntityMobSpawnerTower extends TileEntity {

    private final MobSpawnerTowerLogic mobSpawnerTowerLogic = new MobSpawnerTowerLogic() {

        public void func_98267_a(int p_98267_1_) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlock.blockTowerSpawner, p_98267_1_, 0);
        }

        public World getSpawnerWorld() {
            return worldObj;
        }

        public int getSpawnerX() {
            return xCoord;
        }

        public int getSpawnerY() {
            return yCoord;
        }

        public int getSpawnerZ() {
            return zCoord;
        }

        public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart p_98277_1_) {
            super.setRandomEntity(p_98277_1_);

            if (this.getSpawnerWorld() != null) {
                this.getSpawnerWorld()
                    .markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    };

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.mobSpawnerTowerLogic.readFromNBT(compound);
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.mobSpawnerTowerLogic.writeToNBT(compound);
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        nbttagcompound.removeTag("SpawnPotentials");
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);
    }

    public void updateEntity() {
        super.updateEntity();
        this.mobSpawnerTowerLogic.updateSpawner();
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int id, int type) {
        return this.mobSpawnerTowerLogic.setDelayToMin(id) || this.mobSpawnerTowerLogic.updateSpawnedCount(id)
            || super.receiveClientEvent(id, type);
    }

    public MobSpawnerTowerLogic getMobSpawnerLogic() {
        return this.mobSpawnerTowerLogic;
    }
}
