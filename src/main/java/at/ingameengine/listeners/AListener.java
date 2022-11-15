package at.ingameengine.listeners;

import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

public abstract class AListener implements Listener, IGameStateVisitor {

    Werewolf plugin;
    GameStateManager gameStateManager;
    PlayerEvent event;

    public AListener(Werewolf plugin) {
        this.plugin = plugin;
        gameStateManager = plugin.getGameStateManager();
    }

}
