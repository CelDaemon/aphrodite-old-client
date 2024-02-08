package net.voidgroup.aphrodite.util;

import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class RenderUtil {
    public static PostEffectProcessor createPostEffect(TextureManager textureManager, ResourceManager resourceManager, Framebuffer framebuffer, Identifier identifier) throws IOException {
        return new PostEffectProcessor(textureManager, resourceManager, framebuffer, identifier.withPath("shaders/post/" + identifier.getPath() + ".json"));
    }
}
