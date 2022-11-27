package at.ingameengine.gamestates.states;

import at.ingameengine.entities.roles.ERole;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;

public class FirstDayState extends AGameState {

    public FirstDayState(Werewolf plugin) {
        super(plugin);
        addStep(1, new ArrayList<ERole>() {{
            add(ERole.MAYOR);
        }});
    }

    @Override
    public void start() {
        if (!nextStep()) return;
        runnable();
    }

    @Override
    public void stop() {
        plugin.getServer().getScheduler().cancelTask(runningTaskId);
        plugin.getGameStateManager().setGameState(AGameState.NIGHT_STATE);
    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }

    @Override
    protected void runnable() {
        runningTaskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(
                plugin, () -> {

                }, 1, 20L);
    }
}
