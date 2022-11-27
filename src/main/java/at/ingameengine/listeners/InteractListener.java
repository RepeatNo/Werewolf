package at.ingameengine.listeners;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.InventoryFactory;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener extends AListener {

    public InteractListener(Werewolf plugin) {
        super(plugin);
    }

    PlayerInteractEvent event;
    InventoryFactory inventoryFactory;

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        this.event = event;
        this.inventoryFactory = new InventoryFactory(plugin);
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getItemMeta() == null) return;

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfigManager()
                .readString("items.setup.name"))) {
            InventoryNode setupNode = inventoryFactory.getSetupRootNode();
            player.openInventory(setupNode.getInventory());
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

