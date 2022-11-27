package at.ingameengine.listeners;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.utils.ItemManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

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
        resetPlayer(event);
        player.getInventory().setItem(plugin.getConfigManager().readInt("items.setup.slot") - 1, item);
        Bukkit.broadcastMessage(Werewolf.prefix + "§a" + player.getName() + " §7joined the game!");
    }

    @Override
    public void visit(LobbyState state) {
        WerewolfPlayer player = new WerewolfPlayer(event.getPlayer());
        plugin.addPlayer(player);

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

    @Override
    public void visit(FirstDayState firstDayState) {

    }

    @Override
    public void visit(FirstNightState firstNightState) {

    }

    private void resetPlayer(PlayerEvent event) {
        event.getPlayer().getInventory().clear();
        event.getPlayer().setHealth(20);
        event.getPlayer().setFoodLevel(20);
        event.getPlayer().setExp(0);
        event.getPlayer().setLevel(0);
        event.getPlayer().setFireTicks(0);
        event.getPlayer().setAllowFlight(false);
        event.getPlayer().setFlying(false);
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
    }
}
