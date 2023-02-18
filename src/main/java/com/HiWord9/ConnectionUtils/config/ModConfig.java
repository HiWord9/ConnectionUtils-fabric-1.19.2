package com.HiWord9.ConnectionUtils.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

@Config(name = "connection-utils")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean enabled = true;
    @ConfigEntry.Gui.Tooltip
    public boolean reconnectButtonEnabled = true;
    @ConfigEntry.Gui.Tooltip
    public boolean skipWorldDisconnect = true;
    @ConfigEntry.Gui.Tooltip
    public boolean loadingTerrainFixButton = true;
    @ConfigEntry.Gui.Tooltip
    public boolean mpMenuOpener = false;

    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "connection-utils.json");

    public static final ModConfig INSTANCE = read();

    public static ModConfig read() {
        if (!FILE.exists())
            return new ModConfig().write();

        Reader reader = null;
        try {
            return new Gson().fromJson(reader = new FileReader(FILE), ModConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public ModConfig write() {
        Gson gson = new Gson();
        JsonWriter writer = null;
        try {
            writer = gson.newJsonWriter(new FileWriter(FILE));
            writer.setIndent("    ");

            gson.toJson(gson.toJsonTree(this, ModConfig.class), writer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
        return this;
    }
}
