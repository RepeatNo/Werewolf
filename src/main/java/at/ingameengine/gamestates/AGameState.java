package at.ingameengine.gamestates;

import at.ingameengine.werewolf.Werewolf;

public abstract class AGameState {
    public static final int
            SETUP_STATE = 0,
            LOBBY_STATE = 1,
            DISCUSSION_STATE = 2,
            DAY_STATE = 3, //Voting Phase Day
            NIGHT_STATE = 4, //Voting Phase Night
            ENDING_STATE = 5;
    protected Werewolf plugin;

    public AGameState(Werewolf plugin) {
        this.plugin = plugin;
    }

    public abstract void start();

    public abstract void stop();

    public abstract void accept(IGameStateVisitor commandInspector);

}
