package henrykado.gaiablossom.asm;

import static henrykado.gaiablossom.GaiaBlossom.MODID;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import henrykado.gaiablossom.Config;
import henrykado.gaiablossom.mixin.Mixins;

public class GaiaCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    private static File minecraftHome() {
        return Launch.minecraftHome != null ? Launch.minecraftHome : new File(".");
    }

    public GaiaCore() {
        Config.synchronizeConfiguration(
            new File(
                minecraftHome().toPath()
                    .resolve("config")
                    .toString(),
                MODID + ".cfg"));
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { ClassTransformer.class.getName() };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public String getMixinConfig() {
        return "mixins." + MODID + ".early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return Mixins.getEarlyMixins(loadedCoreMods);
    }

    @LateMixin
    public static class GaiaLateMixins implements ILateMixinLoader {

        @Override
        public String getMixinConfig() {
            return "mixins." + MODID + ".late.json";
        }

        @Override
        public List<String> getMixins(Set<String> loadedMods) {
            return Mixins.getLateMixins(loadedMods);
        }
    }
}
