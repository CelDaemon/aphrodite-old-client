package net.voidgroup.aphrodite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.ResourceFactory;
import net.voidgroup.aphrodite.AphroditeClient;
import net.voidgroup.aphrodite.event.LoadShaderEvent;
import net.voidgroup.aphrodite.event.RenderOverlayEvent;
import net.voidgroup.aphrodite.event.RenderStartEvent;
import net.voidgroup.aphrodite.event.ResizeShaderEvent;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final MinecraftClient client;

    @Inject(method = "render", at = @At("HEAD"))
    private void render(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        RenderStartEvent.EVENT.invoker().start();
    }
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getOverlay()Lnet/minecraft/client/gui/screen/Overlay;", shift = At.Shift.BEFORE))
    private void renderOverlay(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext context) {
        RenderOverlayEvent.EVENT.invoker().render(context);
        RenderSystem.clear(GlConst.GL_DEPTH_BUFFER_BIT, MinecraftClient.IS_SYSTEM_MAC);
    }
    @Inject(method = "loadPrograms", at = @At(value = "TAIL"))
    private void loadPostProcessShaders(ResourceFactory factory, CallbackInfo ci) {
        try {
            LoadShaderEvent.EVENT.invoker().load();
        } catch (IOException ex) {
            AphroditeClient.LOGGER.error("Failed to load shaders", ex);
        }
        var window = client.getWindow();
        ResizeShaderEvent.EVENT.invoker().resize(window.getScaledWidth(), window.getScaledHeight());
    }
    @Inject(method = "onResized", at = @At(value = "HEAD"))
    private void resizeShaders(int width, int height, CallbackInfo ci) {
        ResizeShaderEvent.EVENT.invoker().resize(width, height);
    }
}
