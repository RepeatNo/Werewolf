package at.ingameengine.gamestates;

public abstract class GameState {
    public static final int
            SETUP_STATE = 0,
            LOBBY_STATE = 1,
            DAY_STATE = 2,
            NIGHT_STATE = 3,
            ENDING_STATE = 4;

    public abstract void start();

    public abstract void stop();

}
