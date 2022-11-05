package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;

public class SetupState extends AGameState {
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
