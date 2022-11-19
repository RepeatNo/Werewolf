package at.ingameengine.listeners;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.InventoryManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener extends AListener {

    public InteractListener(Werewolf plugin) {
        super(plugin);
    }

    PlayerInteractEvent event;

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        this.event = event;
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Player player = (Player) event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        InventoryManager inventoryManager = new InventoryManager(plugin);

        if(item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null)
            return;

        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfigManager().readString("items.setup.name"))) {
            player.openInventory(inventoryManager.openGameProfileInventory());
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

