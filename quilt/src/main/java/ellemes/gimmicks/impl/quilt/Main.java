package ellemes.gimmicks.impl.quilt;

import ellemes.gimmicks.impl.Thread;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class Main implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        Thread.initialize();
    }
}
