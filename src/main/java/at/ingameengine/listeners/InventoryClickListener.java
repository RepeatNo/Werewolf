package at.ingameengine.listeners;

import at.ingameengine.entities.InventoryNode;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.InventoryBuilder;
import at.ingameengine.utils.InventoryFactory;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class InventoryClickListener extends AListener {

    public InventoryClickListener(Werewolf plugin) {
        super(plugin);
    }

    InventoryClickEvent event;

    InventoryFactory inventoryFactory;
    InventoryBuilder inventoryBuilder;

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        this.inventoryFactory = new InventoryFactory(plugin);
        this.inventoryBuilder = new InventoryBuilder(plugin);
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Player player = (Player) event.getWhoClicked();

        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

        InventoryNode setup = inventoryFactory.getSetup();

        if (event.getCurrentItem() == null) return;
        InventoryNode clickedInventory = setup.getChildren().get(event.getCurrentItem().getItemMeta().getDisplayName());

        if (inventoryBuilder.compareInventory(setup.getInventory(), Objects.requireNonNull(event.getClickedInventory()))) {
            if (clickedInventory == null) return;
            if (clickedInventory.getInventory() == null)
                return;
            player.openInventory(clickedInventory.getInventory());

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

