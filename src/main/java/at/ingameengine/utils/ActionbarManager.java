package at.ingameengine.utils;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.entities.roles.ARole;
import at.ingameengine.werewolf.Werewolf;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.management.relation.Role;

public class ActionbarManager {

    Werewolf plugin;

    public ActionbarManager(Werewolf plugin) {
        this.plugin = plugin;
    }

    public void sendActionbar(String message, Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public void sendActionbar(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendActionbar(message, player.getPlayer());
        }
    }

    public void sendActionbar(String message, String permission) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission(permission)) {
                sendActionbar(message, player);
            }
        }
    }

    public void sendActionbar(String message, ARole role) {
        for(WerewolfPlayer player : plugin.getPlayers()) {
            if(player.getRole() == role) {
                sendActionbar(message, player.getPlayer());
            }
        }
    }
}
