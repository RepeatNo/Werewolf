package at.ingameengine.werewolf;

import at.ingameengine.commands.implementations.TestCommand;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.listeners.*;
import at.ingameengine.role.RoleManager;
import at.ingameengine.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class Werewolf extends JavaPlugin {

    private GameStateManager gameStateManager;
    public static String prefix;
    private RoleManager roleManager;
    private FileManager configManager;
    private FileManager messageManager;
    private ArrayList<WerewolfPlayer> players;

    @Override
    public void onEnable() {
        players = new ArrayList<>();

        gameStateManager = new GameStateManager(this);
        gameStateManager.setGameState(AGameState.SETUP_STATE);
        roleManager = new RoleManager(this);
        configManager = new FileManager(this, "config.yml");
        messageManager = new FileManager(this, "messages.yml");

        //region FileManager

        prefix = messageManager.readString("prefix");

        //endregion

        //region Commands

        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand(this));

        //endregion

        //region Listeners

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);

        //endregion
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public RoleManager getRoleManager() {
        return roleManager;
    }

    public FileManager getConfigManager() {
        return configManager;
    }
    public FileManager getMessageManager() {
        return messageManager;
    }

    public ArrayList<WerewolfPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(WerewolfPlayer player) {
        this.players.add(player);
    }

    public void removePlayer(WerewolfPlayer player) {
        this.players.remove(player);
    }
}
