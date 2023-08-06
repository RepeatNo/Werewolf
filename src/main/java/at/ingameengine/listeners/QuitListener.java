package at.ingameengine.listeners;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener extends AListener {
    public QuitListener(Werewolf plugin) {
        super(plugin);
    }

    PlayerQuitEvent event;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.event = event;
        event.setQuitMessage(null);
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {

    }

    @Override
    public void visit(LobbyWaitingState state) {
        plugin.removePlayer(event.getPlayer());
    }

    @Override
    public void visit(LobbyReadyState state) {

    }

    @Override
    public void visit(RoleDistributionState state) {

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

    @Override
    public void visit(DiscussionState state) {

    }
}
