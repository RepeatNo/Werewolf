package at.ingameengine.gamestates;

import at.ingameengine.commands.Command;
import at.ingameengine.commands.GameStateVisitor;

public class LobbyState extends GameState implements Command {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void accept(GameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
