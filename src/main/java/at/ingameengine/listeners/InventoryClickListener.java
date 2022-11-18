package at.ingameengine.listeners;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener extends AListener {

    public InventoryClickListener(Werewolf plugin) {
        super(plugin);
    }

    InventoryClickEvent event;

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Player player = (Player) event.getWhoClicked();
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
}

