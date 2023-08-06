package at.ingameengine.listeners;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.ItemManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class JoinListener extends AListener {

    public JoinListener(Werewolf plugin) {
        super(plugin);
    }

    PlayerJoinEvent event;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.event = event;

        event.setJoinMessage(null);

        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Player player = event.getPlayer();

        ItemStack item = new ItemManager().getItem(
                plugin.getConfigManager().readMaterial("items.setup.material"),
                1,
                plugin.getConfigManager().readString("items.setup.name"));

        player.getInventory().setItem(plugin.getConfigManager().readInt("items.setup.slot") - 1, item);

        plugin.getPlayerHandler().resetPlayer(event.getPlayer());
        Bukkit.broadcastMessage(Werewolf.prefix + "§a" + player.getName() + " §7joined the game!");
        if (plugin.getLocationsManager().getLocation("lobby-spawn") != null)
            Objects.requireNonNull(player.getPlayer()).teleport(plugin.getLocationsManager().getLocation("lobby-spawn"));
    }

    @Override
    public void visit(LobbyWaitingState state) {
        LobbyJoin();

        if(plugin.getWerewolfPlayers().size() >= plugin.getConfigManager().readInt("player.min")) {
            plugin.getGameStateManager().setGameState(AGameState.LOBBY_READY_STATE);
        }
    }

    @Override
    public void visit(LobbyReadyState state) {
        LobbyJoin();

        plugin.getReadyManager().placeReadyItem(event.getPlayer());

        if(plugin.getWerewolfPlayers().size() < plugin.getConfigManager().readInt("player.min")) {
            plugin.getGameStateManager().setGameState(AGameState.LOBBY_WAITING_STATE);
        }
    }

    @Override
    public void visit(RoleDistributionState state) {

    }

    private void LobbyJoin() {
        WerewolfPlayer player = new WerewolfPlayer(event.getPlayer());
        plugin.addPlayer(player);

        if (plugin.getLocationsManager().getLocation("lobby-spawn") != null)
            player.getPlayer().teleport(plugin.getLocationsManager().getLocation("lobby-spawn"));

        plugin.getPlayerHandler().resetPlayer(event.getPlayer());
        Bukkit.broadcastMessage(Werewolf.prefix + "§a" + player.getPlayer().getName() + " §7joined the game!");
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
