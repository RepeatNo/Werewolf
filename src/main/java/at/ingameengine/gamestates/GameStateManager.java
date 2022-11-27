package at.ingameengine.gamestates;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.states.*;
import at.ingameengine.werewolf.Werewolf;

public class GameStateManager {

    private final Werewolf plugin;
    private final AGameState[] gameStates;
    private AGameState currentGameState;

    public GameStateManager(Werewolf plugin) {
        this.plugin = plugin;
        gameStates = new AGameState[8];

        gameStates[AGameState.SETUP_STATE] = new SetupState(plugin);
        gameStates[AGameState.LOBBY_STATE] = new LobbyState(plugin);
        gameStates[AGameState.FIRST_NIGHT_STATE] = new FirstNightState(plugin);
        gameStates[AGameState.FIRST_DAY_STATE] = new FirstDayState(plugin);
        gameStates[AGameState.DISCUSSION_STATE] = new DiscussionState(plugin);
        gameStates[AGameState.DAY_STATE] = new DayState(plugin);
        gameStates[AGameState.NIGHT_STATE] = new NightState(plugin);
        gameStates[AGameState.ENDING_STATE] = new EndingState(plugin);
    }

    public void setGameState(int gameStateId) {
        if (currentGameState != null) currentGameState.stop();

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

    public void addVote(WerewolfPlayer player, WerewolfPlayer target) {
        currentGameState.addVote(player, target);
    }
}
