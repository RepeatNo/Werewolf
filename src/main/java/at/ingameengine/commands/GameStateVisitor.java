package at.ingameengine.commands;

import at.ingameengine.gamestates.*;

public interface GameStateVisitor {
    void visit(SetupState state);

    void visit(LobbyState state);

    void visit(DayState state);

    void visit(NightState state);

    void visit(EndingState state);
}
