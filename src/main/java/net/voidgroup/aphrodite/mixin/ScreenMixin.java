package net.voidgroup.aphrodite.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.voidgroup.aphrodite.AphroditeClient;
import net.voidgroup.aphrodite.render.ScreenDetails;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Screen.class)
public class ScreenMixin implements ScreenDetails {
    @ModifyConstant(method = "renderBackground", constant = @Constant(intValue = -1072689136))
    private int startValue(int constant) {
        if(AphroditeClient.BACKGROUND_RENDERER.isDisabled()) return constant;
        return AphroditeClient.BACKGROUND_RENDERER.getColor();
    }
    @ModifyConstant(method = "renderBackground", constant = @Constant(intValue = -804253680))
    private int stopValue(int constant) {
        if(AphroditeClient.BACKGROUND_RENDERER.isDisabled()) return constant;
        return AphroditeClient.BACKGROUND_RENDERER.getColor();
    }
}
