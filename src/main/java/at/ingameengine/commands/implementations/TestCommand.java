package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends ACommand {
    public TestCommand(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void visit(SetupState state) {
        plugin.getGameStateManager().setGameState(AGameState.LOBBY_WAITING_STATE);
    }

    @Override
    public void visit(LobbyWaitingState state) {
        plugin.getGameStateManager().setGameState(AGameState.DAY_STATE);
    }

    @Override
    public void visit(LobbyReadyState state) {

    }

    @Override
    public void visit(RoleDistributionState state) {

    }

    @Override
    public void visit(DayState state) {
        plugin.getGameStateManager().setGameState(AGameState.NIGHT_STATE);
    }

    @Override
    public void visit(NightState state) {
        plugin.getGameStateManager().setGameState(AGameState.DISCUSSION_STATE);
    }

    @Override
    public void visit(DiscussionState state) {
        plugin.getGameStateManager().setGameState(AGameState.ENDING_STATE);
    }

    @Override
    public void visit(EndingState state) {
        plugin.getGameStateManager().setGameState(AGameState.LOBBY_WAITING_STATE);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        gameStateManager.getGameState().accept(this);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(plugin.getInventoryFactory().votingInventoryNode().getInventory());
        }

        return true;
    }
}
