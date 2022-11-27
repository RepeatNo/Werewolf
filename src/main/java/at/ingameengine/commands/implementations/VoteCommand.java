package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VoteCommand extends ACommand {
    public VoteCommand(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void visit(SetupState state) {
        Bukkit.broadcastMessage("SetupState");
    }

    @Override
    public void visit(LobbyState state) {
        Bukkit.broadcastMessage("LobbyState");

    }

    @Override
    public void visit(DayState state) {
        if (!(sender instanceof Player)) return;
        Player player = (Player) sender;
        player.openInventory(plugin.getInventoryFactory().votingInventoryNode().getInventory());
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
