package net.voidgroup.aphrodite.input;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class CallbackKeyBinding extends KeyBinding {
    private final Consumer<Boolean> _callback;
    public CallbackKeyBinding(Identifier key, int code, Identifier category, Consumer<Boolean> callback) {
        super(key.toTranslationKey("key"), code, category.toTranslationKey("category"));
        _callback = callback;
    }

    @Override
    public void setPressed(boolean pressed) {
        _callback.accept(pressed);
    }
}
