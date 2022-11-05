package at.ingameengine.werewolf;

import at.ingameengine.commands.implementations.TestCommand;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.listeners.JoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Werewolf extends JavaPlugin {

    private GameStateManager gameStateManager;
    private ArrayList<Player> players;

    @Override
    public void onEnable() {
        gameStateManager = new GameStateManager(this);
        players = new ArrayList<>();

        gameStateManager.setGameState(AGameState.LOBBY_STATE);


        //region Commands

        this.getCommand("help").setExecutor(new TestCommand(this));

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }
}
