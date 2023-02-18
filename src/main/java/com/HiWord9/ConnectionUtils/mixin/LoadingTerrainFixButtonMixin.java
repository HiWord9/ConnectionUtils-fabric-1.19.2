package com.HiWord9.ConnectionUtils.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DownloadingTerrainScreen.class)
public abstract class LoadingTerrainFixButtonMixin extends Screen {

    protected LoadingTerrainFixButtonMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "render")
    private void addLoadingTerrainFixButton(CallbackInfo ci) {
        Text text = Text.of("LoadingTerrain Fix");
        this.addDrawableChild(new ButtonWidget(4, 4 , 100, 20, text, (button) -> {
            client.setScreen((Screen) new GameMenuScreen(true));
            button.active = false;
        }));
    }
}
