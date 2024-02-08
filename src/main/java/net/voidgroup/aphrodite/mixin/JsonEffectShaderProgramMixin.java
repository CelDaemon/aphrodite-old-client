package net.voidgroup.aphrodite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gl.JsonEffectShaderProgram;
import net.minecraft.client.gl.ShaderStage;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(JsonEffectShaderProgram.class)
public class JsonEffectShaderProgramMixin {
    @Redirect(method = "<init>", at = @At(value = "NEW", target = "(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"))
    private Identifier createIdentifier(String $, @Local(argsOnly = true) String name) {
        var id = new Identifier(name);
        return id.withPath("shaders/program/" + id.getPath() + ".json");
    }
    @Redirect(method = "loadEffect", at = @At(value = "NEW", target = "(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"))
    private static Identifier loadEffectCreateIdentifier(String $, @Local(argsOnly = true) String name, @Local(argsOnly = true) ShaderStage.@NotNull Type type) {
        var id = new Identifier(name);
        return id.withPath("shaders/program/" + id.getPath() + type.getFileExtension());
    }
}
