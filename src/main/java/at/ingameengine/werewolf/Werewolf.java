package at.ingameengine.werewolf;

import at.ingameengine.commands.implementations.TestCommand;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.listeners.JoinListener;
import at.ingameengine.role.RoleManager;
import at.ingameengine.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class Werewolf extends JavaPlugin {

    private GameStateManager gameStateManager;
    public static final String prefix = "§6[§eWerewolf§6]§6 » §r";
    private RoleManager roleManager;
    private ConfigManager configManager;
    private ArrayList<WerewolfPlayer> players;

    @Override
    public void onEnable() {
        players = new ArrayList<>();

        gameStateManager = new GameStateManager(this);
        gameStateManager.setGameState(AGameState.SETUP_STATE);
        roleManager = new RoleManager(this);
        configManager = new ConfigManager(this, "config.yml");

        //region Commands

        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand(this));

        //endregion

        //region Listeners

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);

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

    public ConfigManager getConfigManager() {
        return configManager;
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
