package ellemes.gimmicks.impl;

import ellemes.gimmicks.impl.item.BuildersSatchel;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Thread {
    public static String MOD_ID = "gimmicks";

    public static void initialize() {
        Item item = new BuildersSatchel(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS));
        Registry.register(Registry.ITEM, new ResourceLocation(Thread.MOD_ID, "builders_satchel"), item);
    }
}
