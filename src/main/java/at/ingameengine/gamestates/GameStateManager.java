package at.ingameengine.gamestates;

import at.ingameengine.werewolf.Werewolf;

public class GameStateManager {

    private final Werewolf plugin;
    private final GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager(Werewolf plugin) {
        this.plugin = plugin;
        gameStates = new GameState[5];

        gameStates[GameState.SETUP_STATE] = new SetupState();
        gameStates[GameState.LOBBY_STATE] = new LobbyState();
        gameStates[GameState.DAY_STATE] = new DayState();
        gameStates[GameState.NIGHT_STATE] = new NightState();
        gameStates[GameState.ENDING_STATE] = new EndingState();
    }

    public void setGameState(int gameStateId) {
        if (currentGameState != null)
            currentGameState.stop();

        currentGameState = gameStates[gameStateId];
        currentGameState.start();
    }

    public GameState getGameState() {
        return currentGameState;
    }

    public void stopGameState() {
        if (currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }
}
