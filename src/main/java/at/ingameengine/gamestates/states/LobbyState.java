package at.ingameengine.gamestates.states;

import at.ingameengine.entities.roles.ARole;
import at.ingameengine.entities.roles.Villager;
import at.ingameengine.entities.roles.WerewolfEntity;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

import java.util.HashMap;

public class LobbyState extends AGameState {
    public LobbyState(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

        HashMap<ARole, Integer> roleAmount = new HashMap<>();
        roleAmount.put(new WerewolfEntity(), 1);
        roleAmount.put(new Villager(), 1);
        super.plugin.getRoleManager().allocateRoles(roleAmount);
    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
