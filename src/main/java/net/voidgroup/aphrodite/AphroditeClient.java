package net.voidgroup.aphrodite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AphroditeClient implements ClientModInitializer {
    public static final String NAMESPACE = "aphrodite";
    private static final ModMetadata INFO = FabricLoader.getInstance().getModContainer(NAMESPACE).orElseThrow().getMetadata();
    public static final Logger LOGGER = LoggerFactory.getLogger(INFO.getName());

    @Override
    public void onInitializeClient() {
        LOGGER.info("Test");
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            drawContext.fillGradient(0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), 0x4B000000, 0x4B000000);
        });
    }
}
