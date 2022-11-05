package at.ingameengine.listeners;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener extends AListener {

    public JoinListener(Werewolf plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.event = event;
        event.setJoinMessage(null);
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {

    }

    @Override
    public void visit(LobbyState state) {
        plugin.addPlayer(event.getPlayer());
    }

    @Override
    public void visit(DayState state) {

    }

    @Override
    public void visit(NightState state) {

    }

    @Override
    public void visit(EndingState state) {

    }
}
