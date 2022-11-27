package at.ingameengine.entities;

import at.ingameengine.entities.roles.ARole;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WerewolfPlayer {
    Player player;
    ARole role;
    Boolean isMajor = false;

    public WerewolfPlayer() {
    }

    public WerewolfPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public ARole getRole() {
        return role;
    }

    public Boolean getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor = isMajor;
    }

    public void setRole(ARole role) {
        this.role = role;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void sendMessage(String message) {
        player.sendMessage(Werewolf.prefix + message);
    }

    public boolean compare(WerewolfPlayer player) {
        Bukkit.broadcastMessage("" + this.player.getUniqueId().equals(player.getPlayer().getUniqueId()));
        return this.player.getUniqueId().equals(player.getPlayer().getUniqueId());
    }
}
