package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigManager {
    static final String directory = "plugins//Werewolf//";
    final HashMap<String, String> replacements = new HashMap<String, String>() {
        {
            put("&arrow&", "»");
            put("&block&", "█");
            put("&stroke&", "▕");
            put("&ue&", "ü");
            put("&UE&", "Ü");
            put("&ae&", "ä");
            put("&AE&", "Ä");
            put("&oe&", "ö");
            put("&OE&", "Ö");
            put("&", "§");
        }
    };

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
        return replaceConfigurationChars(config.getString(configName));
    }

    public Integer readConfigInt(String configName) {
        return config.getInt(configName);
    }

    private String replaceConfigurationChars(String input) {
        for (String key : replacements.keySet()) {
            input = input.replaceAll(key, replacements.get(key));
        }
        return input;
    }
}
