package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.utils.ActionbarManager;
import at.ingameengine.werewolf.Werewolf;

public class SetupState extends AGameState {

    ActionbarManager actionbarManager;
    public SetupState(Werewolf plugin) {
        super(plugin);
        actionbarManager = new ActionbarManager(plugin);
    }

    @Override
    public void start() {
        runnable();
    }

    @Override
    public void stop() {

    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }

    private void runnable(){
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                actionbarManager.sendActionbar(Werewolf.prefix + "§7Setup required!", plugin.getConfigManager().readString("permissions.admin"));
            }
        }, 1, 20L);

    }
}
