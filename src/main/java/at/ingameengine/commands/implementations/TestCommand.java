package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TestCommand extends ACommand {
    public TestCommand(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void visit(SetupState state) {
    }

    @Override
    public void visit(LobbyState state) {

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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        gameStateManager.getGameState().accept(this);
        return true;
    }
}
