package at.ingameengine.gamestates;

import at.ingameengine.commands.Command;
import at.ingameengine.commands.GameStateVisitor;

public class SetupState extends GameState implements Command {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void Help() {

    }

    @Override
    public void accept(GameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
