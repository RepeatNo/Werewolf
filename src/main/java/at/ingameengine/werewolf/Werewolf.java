package at.ingameengine.werewolf;

import at.ingameengine.gamestates.GameState;
import at.ingameengine.gamestates.GameStateManager;
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

        gameStateManager.setGameState(GameState.LOBBY_STATE);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
