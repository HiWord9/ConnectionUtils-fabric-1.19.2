package com.HiWord9.ConnectionUtils;

import com.HiWord9.ConnectionUtils.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.ActionResult;

public class ConnectionUtils implements ClientModInitializer {
    public static String MOD_ID = "reconnect-button";

    private static ConfigHolder<ModConfig> configHolder;
    public static ModConfig getConfig() {
        return (ModConfig)configHolder.getConfig();
    }
    public static void saveConfig() {
        configHolder.save();
    }

    @Override
    public void onInitializeClient() {
        configHolder = AutoConfig.register(ModConfig.class, me.shedaniel.autoconfig.serializer.JanksonConfigSerializer::new);
        configHolder.registerSaveListener((manager, data) -> ActionResult.SUCCESS);
    }
}
