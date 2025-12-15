package henrykado.gaiablossom.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;
import henrykado.gaiablossom.GaiaBlossom;

public class ModItem extends Item {

    public ModItem(String name) {
        String uniqueName = GaiaBlossom.MODID + ":" + name;
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName(name);
        this.setTextureName(uniqueName);
        GameRegistry.registerItem(this, uniqueName);
    }
}
