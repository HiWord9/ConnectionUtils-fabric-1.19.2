package com.HiWord9.ConnectionUtils.mixin;

import com.HiWord9.ConnectionUtils.ConnectionUtils;
import com.HiWord9.ConnectionUtils.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow
    @Final
    public GameOptions options;

    @Inject(method = {"close"}, at = {@At("HEAD")})
    private void saveOnClose(CallbackInfo info) {
        options.write();
        ConnectionUtils.saveConfig();
    }
}