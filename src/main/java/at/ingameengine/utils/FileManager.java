package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;

public class FileManager {
    static final String directory = "plugins//Werewolf//";
    final HashMap<String, String> replacements = new HashMap<String, String>() {
        {
            put("<arrow>", "»");
            put("<block>", "█");
            put("<stroke>", "▕");
            put("<ue>", "ü");
            put("<UE>", "Ü");
            put("<ae>", "ä");
            put("<AE>", "Ä");
            put("<oe>", "ö");
            put("<OE>", "Ö");
            put("&", "§");
        }
    };

    Werewolf plugin;
    FileConfiguration fileConfiguration;
    File file;
    String fileName;


    public FileManager(Werewolf plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        saveDefaultFile();
    }

    private void reloadFile() {
        if(file == null)
            file = new File(plugin.getDataFolder(), fileName);

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        InputStream defaultStream = this.plugin.getResource(fileName);

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            fileConfiguration.setDefaults(defaultConfig);
        }
    }

    private FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null)
            reloadFile();
        return fileConfiguration;
    }

    public void saveConfig() {
        try {
            getFileConfiguration().save(file);
        } catch (IOException e) {
            plugin.getLogger().log(java.util.logging.Level.SEVERE, "Could not save config to " + file, e);
        }
    }

    private void saveDefaultFile() {
        if (file == null)
            file = new File(directory + "//" + fileName);

        if(!file.exists())
            this.plugin.saveResource(fileName, false);
    }

    public void setValue(String name, String value) {
        getFileConfiguration().set(name, value);
    }

    public String readString(String name) {
        return replaceConfigurationChars(getFileConfiguration().getString(name));
    }

    public Material readMaterial(String name) {
        return Material.getMaterial(getFileConfiguration().getString(name));
    }

    public Integer readInt(String name) {
        return getFileConfiguration().getInt(name);
    }

    public Boolean readBoolean(String name) {
        return getFileConfiguration().getBoolean(name);
    }

    private String replaceConfigurationChars(String input) {
        for (String key : replacements.keySet()) {
            input = input.replaceAll(key, replacements.get(key));
        }
        return input;
    }

    public void setLocation(String path, Location location) {
        FileConfiguration config = getFileConfiguration();

        // Round the location to the block the player is standing on
        Location roundedLocation = new Location(location.getWorld(),
                Math.floor(location.getX()) + 0.5, Math.floor(location.getY()), Math.floor(location.getZ()) + 0.5);

        config.set(path + ".world", roundedLocation.getWorld().getName());
        config.set(path + ".x", roundedLocation.getX());
        config.set(path + ".y", roundedLocation.getY());
        config.set(path + ".z", roundedLocation.getZ());

        // Set pitch to 0
        config.set(path + ".pitch", 0);

        // Round the yaw
        float roundedYaw = Math.round(location.getYaw() / 90) * 90;
        config.set(path + ".yaw", roundedYaw);
        saveConfig();
    }

    public Location getLocation(String path) {
        FileConfiguration config = getFileConfiguration();
        if (config.contains(path + ".world")) {
            String worldName = config.getString(path + ".world");
            double x = config.getDouble(path + ".x");
            double y = config.getDouble(path + ".y");
            double z = config.getDouble(path + ".z");
            float yaw = (float) config.getDouble(path + ".yaw");
            float pitch = (float) config.getDouble(path + ".pitch");

            return new Location(plugin.getServer().getWorld(worldName), x, y, z, yaw, pitch);
        }
        return null;
    }
}
