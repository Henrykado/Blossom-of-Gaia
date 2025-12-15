package henrykado.gaiablossom.common.item;

import net.minecraft.item.Item;

public class ModItems {

    public static Item glass_item_frame;

    public static Item recall_potion;
    public static LivingJetpack living_jetpack;

    public static Item invisibilityCloak;
    public static Item cloudPendant;
    public static Item dodgeRing;

    public static void init() {
        // recall_potion = new RecallPotion();
        // living_jetpack = new LivingJetpack();

        invisibilityCloak = new ItemInvisibilityCloak();
        cloudPendant = new ItemCloudPendant();
        dodgeRing = new ItemDodgeRing();

        // glass_item_frame = new ItemGlassItemFrame();
    }
}
