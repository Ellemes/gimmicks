package ellemes.gimmicks.impl.quilt;

import ellemes.gimmicks.impl.item.BuildersSatchel;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class Main implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        var item = new BuildersSatchel(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS));
        Registry.register(Registry.ITEM, new ResourceLocation("gimmicks", "builders_satchel"), item);
    }
}
