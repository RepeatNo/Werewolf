package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TestCommand extends ACommand {
    public TestCommand(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void visit(SetupState state) {
        plugin.getGameStateManager().setGameState(AGameState.LOBBY_STATE);
    }

    @Override
    public void visit(LobbyState state) {
        plugin.getGameStateManager().setGameState(AGameState.DAY_STATE);
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
        plugin.getGameStateManager().setGameState(AGameState.LOBBY_STATE);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        gameStateManager.getGameState().accept(this);
        Bukkit.broadcastMessage("TestCommand execute");
        return true;
    }
}
