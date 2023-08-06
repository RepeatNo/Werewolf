package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ExperienceBarManager {

    Werewolf plugin;

    int runnableTaskId;

    public ExperienceBarManager(Werewolf plugin) {
        this.plugin = plugin;
    }

    public void setExpBar(float exp) {
        plugin.getWerewolfPlayers().forEach(player -> setExpBar(player.getPlayer(), exp));
    }

    public void setExpBar(Player player, float exp) {
        player.setExp(exp);
    }

    public void startLoadingExpBar() {
        runnableTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            float currentExpBarAmount = 0.0f;

            @Override
            public void run() {
                if(currentExpBarAmount >= 1.0f) currentExpBarAmount = 0.0f;
                setExpBar(currentExpBarAmount);
                currentExpBarAmount += 0.01f;
            }
        }, 1, 1L);
    }

    public void stopLoadingExpBar() {
        if (runnableTaskId != -1) {
            Bukkit.getScheduler().cancelTask(runnableTaskId); // Cancel the runnable task
        }
    }

}
