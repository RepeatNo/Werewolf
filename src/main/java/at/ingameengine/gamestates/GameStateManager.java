package at.ingameengine.gamestates;

import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;

public class GameStateManager {

    private final Werewolf plugin;
    private final AGameState[] gameStates;
    private AGameState currentGameState;

    public GameStateManager(Werewolf plugin) {
        this.plugin = plugin;
        gameStates = new AGameState[5];

        gameStates[AGameState.SETUP_STATE] = new SetupState();
        gameStates[AGameState.LOBBY_STATE] = new LobbyState();
        gameStates[AGameState.DAY_STATE] = new DayState();
        gameStates[AGameState.NIGHT_STATE] = new NightState();
        gameStates[AGameState.ENDING_STATE] = new EndingState();
    }

    public void setGameState(int gameStateId) {
        if (currentGameState != null)
            currentGameState.stop();

        currentGameState = gameStates[gameStateId];
        currentGameState.start();
    }

    public AGameState getGameState() {
        return currentGameState;
    }

    public void stopGameState() {
        if (currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }
}
