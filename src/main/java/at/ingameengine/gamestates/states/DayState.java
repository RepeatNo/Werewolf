package at.ingameengine.gamestates.states;

import at.ingameengine.entities.roles.ERole;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.utils.VotingManager;
import at.ingameengine.werewolf.Werewolf;

public class DayState extends AGameState {

    public DayState(Werewolf plugin) {
        super(plugin);
        votingManager = new VotingManager(plugin, ERole.NONE);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
