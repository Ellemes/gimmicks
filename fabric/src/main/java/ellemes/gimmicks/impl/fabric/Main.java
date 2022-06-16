package ellemes.gimmicks.impl.fabric;

import ellemes.gimmicks.impl.Thread;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        Thread.initialize();
    }
}
