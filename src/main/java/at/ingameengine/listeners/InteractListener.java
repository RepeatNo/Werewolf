package at.ingameengine.listeners;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.InventoryFactory;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfigManager().readString("items.setup.name"))) {
            InventoryNode setupNode = inventoryFactory.getSetupRootNode();
            player.openInventory(setupNode.getInventory());
        }
    }

    @Override
    public void visit(LobbyWaitingState state) {

    }

    @Override
    public void visit(LobbyReadyState state) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getItemMeta() == null) return;

        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfigManager().readString("items.ready.name")))
            return;

        plugin.getReadyManager().addReadyPlayer(player);
        plugin.getPlayerHandler().removeItemFromInventory(player, "ready");

        plugin.getActionbarManager().stopActionBar();
        plugin.getActionbarManager().startActionBar("§a" + plugin.getReadyManager().getReadyPlayers().size() + " of " + plugin.getWerewolfPlayers().size() + " players are ready!");

        List<Player> werewolfPlayerPlayers = plugin.getWerewolfPlayers().stream().map(at.ingameengine.entities.WerewolfPlayer::getPlayer).collect(Collectors.toList());

        if (plugin.getReadyManager().getReadyPlayers().stream().allMatch(rp -> werewolfPlayerPlayers.contains(rp.getPlayer()))) {
            gameStateManager.setGameState(AGameState.ROLE_DISTRIBUTION_STATE);
        }

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

