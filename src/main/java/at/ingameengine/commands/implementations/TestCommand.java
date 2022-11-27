package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;

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

    }

    @Override
    public void visit(NightState state) {

    }

    @Override
    public void visit(DiscussionState state) {

    }

    @Override
    public void visit(FirstDayState firstDayState) {

    }

    @Override
    public void visit(FirstNightState firstNightState) {

    }

    @Override
    public void visit(EndingState state) {
    }
}
