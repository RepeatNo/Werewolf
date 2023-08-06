package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ReadyManager {

    private final Werewolf plugin;

    public ReadyManager(Werewolf plugin) {
        this.plugin = plugin;
    }

    ArrayList<Player> readyPlayers = new ArrayList<>();

    public void addReadyPlayer(Player player) {
        readyPlayers.add(player);
    }

    public void removeReadyPlayer(Player player) {
        readyPlayers.remove(player);
    }

    public ArrayList<Player> getReadyPlayers() {
        return readyPlayers;
    }

    public void placeReadyItem(Player player) {
        plugin.getPlayerHandler().loadItemIntoInventory(player, "ready");
    }

    public void placeReadyItem() {
        plugin.getWerewolfPlayers().forEach(player -> placeReadyItem(player.getPlayer()));
    }
}
