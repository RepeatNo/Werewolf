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

        if (event.getCurrentItem() == null) return;

        InventoryNode setup = inventoryFactory.getSetup();

        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

        if (itemName.equalsIgnoreCase("§cBack")) {
            InventoryNode parent = setup.getNode(event.getInventory()).getParent();
            if (parent == null) return;
            player.openInventory(parent.getInventory());
            return;
        }

        InventoryNode clickedInventory = setup
                .getNode(event.getCurrentItem().getItemMeta().getDisplayName());

        if (InventoryBuilder.compareInventory(setup.getInventory(), event.getClickedInventory())) {
            if (clickedInventory == null) return;
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

