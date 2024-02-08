package net.voidgroup.aphrodite.mixin;

import net.minecraft.client.gui.screen.ChatScreen;
import net.voidgroup.aphrodite.render.ScreenDetails;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChatScreen.class)
public class ChatScreenMixin implements ScreenDetails {
    @Override
    public boolean void_client$excludeDarken() {
        return true;
    }
}
