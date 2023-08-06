package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

public class LobbyWaitingState extends AGameState {

    public LobbyWaitingState(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void start() {
        plugin.getServer().getWorlds().get(0).setTime(6000);
        plugin.getExpBarManager().startLoadingExpBar();
        plugin.getActionbarManager().startActionBar("§aWaiting for players...");

        if(plugin.getWerewolfPlayers().size() >= plugin.getConfigManager().readInt("player.min")) {
            plugin.getGameStateManager().setGameState(AGameState.LOBBY_READY_STATE);
        }
    }

    @Override
    public void stop() {
        plugin.getExpBarManager().stopLoadingExpBar();
        plugin.getActionbarManager().stopActionBar();
    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
