package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    static final String directory = "plugins//Werewolf//";

    Werewolf plugin;
    YamlConfiguration config = new YamlConfiguration();
    File file;

    public ConfigManager(Werewolf plugin, String fileName) {
        this.plugin = plugin;
        this.file = new File(directory + "//" + fileName);
        loadConfig();
    }

    public void loadConfig() {
        try {
            if (!file.exists()) {
                new File(directory).mkdirs();
                file.createNewFile();
            }
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConfig(String configName, String value) {
        config.set(configName, value);
    }

    public String readConfigString(String configName) {
        return config.getString(configName);
    }
}
