package at.ingameengine.gamestates.states;

import at.ingameengine.entities.roles.ERole;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;

public class FirstNightState extends AGameState {

    public FirstNightState(Werewolf plugin) {
        super(plugin);
        addStep(1, new ArrayList<ERole>() {{
            add(ERole.CUPID);
            add(ERole.DOCTOR);
            add(ERole.SEER);
        }});
        addStep(2, new ArrayList<ERole>() {{
            add(ERole.WEREWOLF_ENTITY);
        }});
        addStep(3, new ArrayList<ERole>() {{
            add(ERole.WITCH);
        }});
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
