package at.ingameengine.gamestates.states;

import at.ingameengine.entities.roles.ERole;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.utils.VotingManager;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;
import java.util.HashMap;

public class NightState extends AGameState {

    public NightState(Werewolf plugin) {
        super(plugin);
        super.votingSteps = new HashMap<Integer, ArrayList<VotingManager>>() {
            {
                put(1, new ArrayList<VotingManager>() {{
                    add(new VotingManager(plugin, ERole.CUPID));
                    add(new VotingManager(plugin, ERole.DOCTOR));
                    add(new VotingManager(plugin, ERole.SEER));
                }});
                put(2, new ArrayList<VotingManager>() {{
                    add(new VotingManager(plugin, ERole.WEREWOLF_ENTITY));
                }});
                put(3, new ArrayList<VotingManager>() {{
                    add(new VotingManager(plugin, ERole.WITCH));
                }});
            }
        };
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }
}
