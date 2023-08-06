package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerHandler {
    Werewolf plugin;

    public PlayerHandler(Werewolf plugin) {
        this.plugin = plugin;
    }

    public void resetPlayer() {
        plugin.getWerewolfPlayers().forEach(player -> resetPlayer(player.getPlayer()));
    }

    public void resetPlayer(Player player) {
        Objects.requireNonNull(player.getPlayer()).getInventory().clear();
        player.getPlayer().setHealth(20);
        player.getPlayer().setFoodLevel(20);
        player.getPlayer().setExp(0);
        player.getPlayer().setLevel(0);
        player.getPlayer().setFireTicks(0);
        player.getPlayer().setAllowFlight(false);
        player.getPlayer().setFlying(false);
        player.getPlayer().setGameMode(GameMode.ADVENTURE);
    }

    public void loadItemIntoInventory(Player player, String itemName) {
        ItemStack item = new ItemManager().getItem(
                plugin.getConfigManager().readMaterial("items." + itemName + ".material"),
                1,
                plugin.getConfigManager().readString("items." + itemName + ".name"));

        player.getInventory().setItem(plugin.getConfigManager().readInt("items." + itemName + ".slot") - 1, item);
    }

    public void removeItemFromInventory(Player player, String itemName) {
        player.getInventory().setItem(plugin.getConfigManager().readInt("items." + itemName + ".slot") - 1, null);
    }

    public void removeItemFromInventory(String itemName) {
        plugin.getWerewolfPlayers().forEach(player -> removeItemFromInventory(player.getPlayer(), itemName));
    }


}
