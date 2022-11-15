package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    FileConfiguration config;
    Werewolf plugin;

    public ConfigManager(Werewolf plugin) {
        this.config = plugin.getConfig();
        this.plugin = plugin;
    }
}
