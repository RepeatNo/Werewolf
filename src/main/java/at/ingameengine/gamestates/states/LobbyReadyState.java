package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

public class LobbyReadyState extends AGameState {

    public LobbyReadyState(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void start() {
        plugin.getActionbarManager().startActionBar("§a"
                + plugin.getReadyManager().getReadyPlayers().size()
                + " of " + plugin.getWerewolfPlayers().size()
                + " players are ready!");

        plugin.getReadyManager().placeReadyItem();
    }

    @Override
    public void stop() {
        plugin.getExpBarManager().setExpBar(0f);
    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
