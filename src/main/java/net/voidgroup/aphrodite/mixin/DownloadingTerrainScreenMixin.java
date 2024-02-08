package net.voidgroup.aphrodite.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.voidgroup.aphrodite.render.ScreenDetails;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadingTerrainScreenMixin implements ScreenDetails {
    @Override
    public boolean void_client$excludeDarken() {
        return true;
    }
    @Override
    public boolean void_client$excludeBlur() {
        return true;
    }
}
