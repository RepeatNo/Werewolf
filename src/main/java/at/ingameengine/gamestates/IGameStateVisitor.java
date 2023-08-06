package at.ingameengine.gamestates;

import at.ingameengine.gamestates.states.*;

public interface IGameStateVisitor {
    void visit(SetupState state);

    void visit(LobbyWaitingState state);

    void visit(LobbyReadyState state);
    void visit(RoleDistributionState state);

    void visit(DayState state);

    void visit(NightState state);

    void visit(EndingState state);

    void visit(DiscussionState state);
}
