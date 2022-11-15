package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

public class DiscussionState extends AGameState {
    public DiscussionState(Werewolf plugin) {
        super(plugin);
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
