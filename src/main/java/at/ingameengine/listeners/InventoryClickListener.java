package at.ingameengine.listeners;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.entities.inventory.button.AInventoryButton;
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
    Player player;

    InventoryFactory inventoryFactory;
    InventoryBuilder inventoryBuilder;

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        this.inventoryFactory = plugin.getInventoryFactory();
        this.inventoryBuilder = plugin.getInventoryBuilder();

        player = (Player) event.getWhoClicked();
        if (!(player.getGameMode() == GameMode.CREATIVE)) event.setCancelled(true);
        if (event.getCurrentItem() == null) return;

        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        executeButton(inventoryFactory.getSetupRootNode());
    }

    @Override
    public void visit(LobbyState state) {

    }

    @Override
    public void visit(DayState state) {
        executeButton(inventoryFactory.votingInventoryNode());
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

    private void executeButton(InventoryNode node) {
        InventoryNode clickedInventory = node
                .getNode(event.getInventory());
        if (clickedInventory == null) return;

        AInventoryButton invButton = clickedInventory.getInventoryButton(event.getCurrentItem());
        if (invButton == null) return;
        invButton.Execute(plugin, event);
    }
}

