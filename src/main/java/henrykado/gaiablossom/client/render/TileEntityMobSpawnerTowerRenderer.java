package henrykado.gaiablossom.client.render;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import henrykado.gaiablossom.common.block.tile.MobSpawnerTowerLogic;
import henrykado.gaiablossom.common.block.tile.TileEntityMobSpawnerTower;

public class TileEntityMobSpawnerTowerRenderer extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntityMobSpawnerTower spawner, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
        func_147517_a(spawner.getMobSpawnerLogic(), x, y, z, f);
        GL11.glPopMatrix();
    }

    public static void func_147517_a(MobSpawnerTowerLogic spawnerLogic, double x, double y, double z, float f) {
        Entity entity = spawnerLogic.func_98281_h();

        if (entity != null) {
            entity.setWorld(spawnerLogic.getSpawnerWorld());
            float f1 = 0.4375F;
            GL11.glTranslatef(0.0F, 0.4F, 0.0F);
            GL11.glRotatef(
                (float) (spawnerLogic.field_98284_d
                    + (spawnerLogic.field_98287_c - spawnerLogic.field_98284_d) * (double) f) * 10.0F,
                0.0F,
                1.0F,
                0.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.4F, 0.0F);
            GL11.glScalef(f1, f1, f1);
            entity.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, f);
        }
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_,
        float p_147500_8_) {
        this.renderTileEntityAt(
            (TileEntityMobSpawnerTower) p_147500_1_,
            p_147500_2_,
            p_147500_4_,
            p_147500_6_,
            p_147500_8_);
    }
}
