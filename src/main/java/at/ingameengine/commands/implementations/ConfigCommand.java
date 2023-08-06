package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommand extends ACommand{
    public ConfigCommand(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void visit(SetupState state) {

    }

    @Override
    public void visit(LobbyWaitingState state) {

    }

    @Override
    public void visit(LobbyReadyState state) {

    }

    @Override
    public void visit(RoleDistributionState state) {

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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        gameStateManager.getGameState().accept(this);

        if (sender instanceof Player) {
            ((Player) sender).openInventory(plugin.getInventoryFactory().setupInventoryNode().getInventory());
        }

        return true;
    }
}
