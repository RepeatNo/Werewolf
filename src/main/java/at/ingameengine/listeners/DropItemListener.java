package at.ingameengine.listeners;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener extends AListener {

    public DropItemListener(Werewolf plugin) {
        super(plugin);
    }

    PlayerDropItemEvent event;

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        this.event = event;
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Player player = event.getPlayer();
        if(!(player.getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @Override
    public void visit(LobbyState state) {

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

    @Override
    public void visit(FirstDayState firstDayState) {

    }

    @Override
    public void visit(FirstNightState firstNightState) {

    }
}
