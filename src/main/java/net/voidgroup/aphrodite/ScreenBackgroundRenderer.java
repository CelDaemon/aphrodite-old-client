package net.voidgroup.aphrodite;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.voidgroup.aphrodite.event.RenderOverlayEvent;
import net.voidgroup.aphrodite.event.RenderStartEvent;
import net.voidgroup.aphrodite.input.CallbackKeyBinding;
import net.voidgroup.aphrodite.render.ScreenDetails;

public class ScreenBackgroundRenderer {
    public static final Identifier KEY = new Identifier(AphroditeClient.NAMESPACE, "background_render");
    public static final Identifier CATEGORY = new Identifier(AphroditeClient.NAMESPACE, "main");
    private final MinecraftClient _client;
    private boolean _init = false;
    private boolean _enabled = false;
    private long _blurChangeTime;
    private float _blurOffset;
    private boolean _blurState;
    private float _blurProgress;
    private float _blurEasedProgress;
    private long _darkenChangeTime;
    private float _darkenOffset;
    private boolean _darkenState;
    private float _darkenProgress;
    private float _darkenEasedProgress;
    public ScreenBackgroundRenderer(MinecraftClient client) {
        _client = client;
    }

    public void enable() {
        _enabled = true;
        if(_init) return;
        KeyBindingHelper.registerKeyBinding(new CallbackKeyBinding(KEY, InputUtil.UNKNOWN_KEY.getCode(), CATEGORY, pressed -> {
            if(!pressed) return;
            if(_enabled) disable();
            else enable();
        }));
        RenderStartEvent.EVENT.register(() -> {
            refreshBlur();
            refreshDarken();
        });
        RenderOverlayEvent.EVENT.register((drawContext) -> {
            if(_client.currentScreen != null) return;
            var color = getColor();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            drawContext.fillGradient(0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), color, color);
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
        });
        _init = true;
    }
    public void disable() {
        _enabled = false;
    }
    public boolean isDisabled() {
        return !_enabled;
    }
    public int getColor() {
        return (int)(75 * _darkenEasedProgress) << 24;
    }

    public void refreshBlur() {
        var currentTime = Util.getMeasuringTimeMs();
        var screenDetails = (ScreenDetails) _client.currentScreen;
        var newState = _enabled && _client.world != null && _client.currentScreen != null && !screenDetails.void_client$excludeBlur();
        if(newState != _blurState) {
            _blurOffset = getProgress(currentTime, _blurChangeTime, _blurOffset, _blurState);
            _blurChangeTime = currentTime;
            _blurState = newState;
        }
        _blurProgress = getProgress(currentTime, _blurChangeTime, _blurOffset, _blurState);
        _blurEasedProgress = ease(_blurProgress);
    }
    public void refreshDarken() {
        var currentTime = Util.getMeasuringTimeMs();
        var screenDetails = (ScreenDetails) _client.currentScreen;
        var newState = _enabled && _client.world != null && _client.currentScreen != null && !screenDetails.void_client$excludeDarken();
        if(newState != _darkenState) {
            _darkenOffset = getProgress(currentTime, _darkenChangeTime, _darkenOffset, _darkenState);
            _darkenChangeTime = currentTime;
            _darkenState = newState;
        }
        _darkenProgress = getProgress(currentTime, _darkenChangeTime, _darkenOffset, _darkenState);
        _darkenEasedProgress = ease(_darkenProgress);
    }
    public static float getProgress(long currentTime, long startTime, float offset, boolean direction) {
        if(direction) {
            return Math.min(offset + (currentTime - startTime) / (float) 200, 1);
        } else {
            return Math.max(offset + (startTime - currentTime) / (float) 200, 0);
        }
    }
    public static float ease(float x) {
        return x * (2 - x);
    }
}
