package at.ingameengine.listeners;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.entities.inventory.button.AInventoryButton;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.InventoryBuilder;
import at.ingameengine.utils.InventoryFactory;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
        this.inventoryFactory = plugin.getInventoryFactory();
        this.inventoryBuilder = plugin.getInventoryBuilder();
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        handleInventoryClickEvent(event);
    }

    @Override
    public void visit(LobbyWaitingState state) {
        handleInventoryClickEvent(event);
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

    public void handleInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!(player.getGameMode() == GameMode.CREATIVE)) event.setCancelled(true);
        if (event.getCurrentItem() == null) return;

        InventoryNode setup = inventoryFactory.getSetupRootNode();

        InventoryNode clickedInventory = setup
                .getNode(event.getInventory());
        if (clickedInventory == null) return;

        AInventoryButton invButton = clickedInventory.getInventoryButton(event.getCurrentItem());
        if (invButton == null) return;
        invButton.Execute(event);

        //player.openInventory(clickedInventory.getInventory());

        /*if (itemName.equalsIgnoreCase("§cBack")) {
            InventoryNode parent = setup.getNode(event.getInventory()).getParent();
            if (parent == null) return;
            player.openInventory(parent.getInventory());
            return;
        }*/
    }
}

