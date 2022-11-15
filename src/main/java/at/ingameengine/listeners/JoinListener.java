package at.ingameengine.listeners;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener extends AListener {

    public JoinListener(Werewolf plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.event = event;
        event.setJoinMessage(null);
        gameStateManager.getGameState().accept(this);
    }

    @Override
    public void visit(SetupState state) {
        Bukkit.broadcastMessage(Werewolf.prefix + "§a" + new WerewolfPlayer(event.getPlayer()).getPlayer().getName() + " §7joined the game!");
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
}
