package com.HiWord9.ConnectionUtils.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreenFactory {

    public static Screen create(Screen parent) {
        ModConfig currentConfig = ModConfig.INSTANCE, defaultConfig = new ModConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.connecting-utils.title"))
                .setSavingRunnable(currentConfig::write);

        ConfigCategory category = builder.getOrCreateCategory(Text.empty());
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.connecting-utils.enabled.title"), currentConfig.enabled)
                .setTooltip(Text.translatable("config.connecting-utils.enabled.tooltip"))
                .setSaveConsumer(newConfig -> {
                    if (currentConfig.enabled != newConfig) {
                        currentConfig.enabled = newConfig;
                        MinecraftClient.getInstance().reloadResources();
                    }
                })
                .setDefaultValue(defaultConfig.enabled)
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.connecting-utils.reconnectButtonEnabled.title"), currentConfig.reconnectButtonEnabled)
                .setTooltip(Text.translatable("config.connecting-utils.reconnectButtonEnabled.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.reconnectButtonEnabled = newConfig)
                .setDefaultValue(defaultConfig.reconnectButtonEnabled)
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.connecting-utils.skipWorldDisconnect.title"), currentConfig.skipWorldDisconnect)
                .setTooltip(Text.translatable("config.connecting-utils.skipWorldDisconnect.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.skipWorldDisconnect = newConfig)
                .setDefaultValue(defaultConfig.skipWorldDisconnect)
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.connecting-utils.loadingTerrainFixButton.title"), currentConfig.loadingTerrainFixButton)
                .setTooltip(Text.translatable("config.connecting-utils.loadingTerrainFixButton.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.loadingTerrainFixButton = newConfig)
                .setDefaultValue(defaultConfig.loadingTerrainFixButton)
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.connecting-utils.mpMenuOpener.title"), currentConfig.mpMenuOpener)
                .setTooltip(Text.translatable("config.connecting-utils.mpMenuOpener.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.mpMenuOpener = newConfig)
                .setDefaultValue(defaultConfig.mpMenuOpener)
                .build());

        return builder.build();
    }
}