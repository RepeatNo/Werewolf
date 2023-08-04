package at.ingameengine.listeners;

import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

public class CancelledListeners implements Listener {
    Werewolf plugin;
    GameStateManager gameStateManager;

    public CancelledListeners(Werewolf plugin) {
        this.plugin = plugin;
        gameStateManager = plugin.getGameStateManager();
    }

    @EventHandler
    public void onEvent(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEvent(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEvent(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
