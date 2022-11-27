package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

public class LobbyState extends AGameState {
    public LobbyState(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        plugin.getVotingManager().initVotes();

        //TODO super.plugin.getRoleManager().allocateRoles(roleAmount);
    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
