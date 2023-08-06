package at.ingameengine.gamestates;

import at.ingameengine.werewolf.Werewolf;

public abstract class AGameState {
    public static final int
            SETUP_STATE = 0,
            LOBBY_WAITING_STATE = 1,
            LOBBY_READY_STATE = 2,
            ROLE_DISTRIBUTION_STATE = 3,
            DISCUSSION_STATE = 4,
            DAY_STATE = 5, //Voting Phase Day
            NIGHT_STATE = 6, //Voting Phase Night
            ENDING_STATE = 7;
    protected Werewolf plugin;

    public AGameState(Werewolf plugin) {
        this.plugin = plugin;
    }

    public abstract void start();

    public abstract void stop();

    public abstract void accept(IGameStateVisitor commandInspector);

}
