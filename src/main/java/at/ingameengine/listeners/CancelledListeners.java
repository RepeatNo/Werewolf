package at.ingameengine.listeners;

import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.GameMode;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherEvent;

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
    public void onEvent(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(BlockPlaceEvent event) {
        event.setCancelled(true);
    }
}
