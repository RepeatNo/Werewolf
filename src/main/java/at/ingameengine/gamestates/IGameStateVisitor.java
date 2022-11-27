package at.ingameengine.gamestates;

import at.ingameengine.gamestates.states.*;

public interface IGameStateVisitor {
    void visit(SetupState state);

    void visit(LobbyState state);

    void visit(DayState state);

    void visit(NightState state);

    void visit(EndingState state);

    void visit(DiscussionState state);

    void visit(FirstDayState firstDayState);

    void visit(FirstNightState firstNightState);
}
